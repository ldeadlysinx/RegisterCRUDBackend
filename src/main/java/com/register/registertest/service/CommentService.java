package com.register.registertest.service;

import com.register.registertest.dto.CommentDTO;
import com.register.registertest.entity.BoardEntity;
import com.register.registertest.entity.CommentEntity;
import com.register.registertest.entity.User;
import com.register.registertest.repository.BoardRepository;
import com.register.registertest.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    private final BoardRepository boardRepository;

    @Autowired
    public CommentService(BoardRepository boardRepository, CommentRepository commentRepository) {
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    public Long save(CommentDTO commentDTO){
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO,boardEntity);
            return commentRepository.save(commentEntity).getId();


        }else{
            return null;
        }

    }

    public List<CommentDTO> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

    public List<CommentEntity> getCommentsByBoardId(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findByBoardEntityId_Id(boardId);
        return commentEntityList;
    }

    public void saveComment(Long boardId,CommentEntity commentEntity, User user) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        System.out.println(boardEntity.getTitle());
        commentEntity.setBoardEntity(boardEntity);
        commentEntity.setCommentWriter(user.getName());
        commentRepository.save(commentEntity);
    }

    public boolean checkUser(Long commentId, User user) {
        CommentEntity commentEntity = commentRepository.findById(commentId).get();
        if(commentEntity.getCommentWriter().equals(user.getName())){
            return true;
        }else{
            return false;
        }

    }

    public void deleteComment(Long commentId, User user) {
        System.out.println("여기까지 실행1");
        CommentEntity commentEntity = commentRepository.findById(commentId).orElse(null);
        System.out.println("여기까지 실행2");
        if (commentEntity != null) {
            if (commentEntity.getCommentWriter().equals(user.getName())) {
                commentRepository.deleteById(commentId);
            } else {
                System.out.println("댓글아이디 유저와 토큰 유저가 다릅니다");
            }
        } else {
            System.out.println("삭제할 댓글이 존재하지 않습니다");
        }
    }

    public long getCommentCountBoard(Long boardId) {
        return commentRepository.countByBoardEntityId(boardId);
    }

    public List<CommentEntity> getMyComments(User user) {
        String username= user.getName();
        List<CommentEntity> comments = commentRepository.findByCommentWriter(username);
        // 게시글의 ID를 포함하여 반환

        return comments;
    }

    public Long getBoardIdCommentId(Long commentId) {
        CommentEntity commentEntity = commentRepository.findById(commentId).orElse(null);
        Long boardId = commentEntity.getBoardId();
        return boardId;
    }
}
