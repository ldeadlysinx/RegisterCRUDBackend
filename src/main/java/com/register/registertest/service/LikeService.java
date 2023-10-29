package com.register.registertest.service;

import com.register.registertest.entity.BoardEntity;
import com.register.registertest.entity.Like;
import com.register.registertest.entity.User;
import com.register.registertest.repository.BoardRepository;
import com.register.registertest.repository.CommentRepository;
import com.register.registertest.repository.LikeRepository;
import com.register.registertest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public boolean checkUser(Long boardId, User user) {
        if (likeRepository.existsByBoardEntityIdAndUserId(boardId, user.getId())) {
            return true;
        }else{
            return false;
        }
    }


    public void saveLike(Long boardId, Like like, User user) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        User getUser = userRepository.findById(user.getId()).get();
        if (likeRepository.existsByBoardEntityIdAndUserId(boardId, user.getId())) {
            System.out.println("이미 좋아요를 누른 게시판");
        }else{
            like.setUser(getUser);
            like.setBoardEntity(boardEntity);
            likeRepository.save(like);
            System.out.println("저장완료");
        }
    }

    public void deleteLike(Long boardId, User user) {
        Like like = likeRepository.findByBoardEntityIdAndUserId(boardId, user.getId());
        if(likeRepository.existsByBoardEntityIdAndUserId(boardId,user.getId())){
            likeRepository.delete(like);
            System.out.println("좋아요 삭제");
        }else{
            System.out.println("존재하지 않는 좋아요");
        }
    }

    public long getLikeCountBoard(Long boardId) {
        return likeRepository.countByBoardEntityId(boardId);
    }

    public List<Like> getMyLike(User user) {
        List<Like> like = likeRepository.findByUserId(user.getId());
        return like;
    }

    public Long getBoardIdByMyLike(Long likeId) {
        Like like = likeRepository.findById(likeId).orElse(null);
        Long boardId = like.getBoardId();
        return boardId;
    }
}

