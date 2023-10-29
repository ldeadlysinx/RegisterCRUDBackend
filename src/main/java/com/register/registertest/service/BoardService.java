package com.register.registertest.service;

import com.register.registertest.dto.BoardDTO;
import com.register.registertest.dto.MemberDTO;
import com.register.registertest.entity.BoardEntity;
import com.register.registertest.entity.MemberEntity;
import com.register.registertest.entity.User;
import com.register.registertest.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private static final int BLOCK_PAGE_NUM_COUNT = 5;
    private static final int PAGE_POST_COUNT=4;


    @Transactional
    public void savePost(BoardDTO boardDTO){
      boardRepository.save(boardDTO.toEntity()).getId();
    }

    @Transactional
    public List<BoardDTO> getBoardList(Integer pageNum) {

        Page<BoardEntity> page =boardRepository.findAll(PageRequest.of(pageNum-1,PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC,"createdDate")));

        List<BoardEntity> boardEntityList = page.getContent();
        List<BoardDTO> boardDTOList= new ArrayList<>();

        for(BoardEntity boardEntity : boardEntityList){
            boardDTOList.add(this.convertEntityToDto(boardEntity));
        }
        return boardDTOList;
    }

    public BoardDTO getPost(Long id) {
        Optional<BoardEntity> boardWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardWrapper.get();

        BoardDTO boardDTO = BoardDTO.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();

        return boardDTO;
    }

    public void update(BoardDTO boardDTO) {
        boardRepository.save(boardDTO.toEntity()).getId();
    }

    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    public List<BoardDTO> searchPosts(String keyword) {
        List<BoardEntity> boardEntityList = boardRepository.findByTitleContaining(keyword);
        List<BoardDTO> boardDTOList= new ArrayList<>();

        if(boardEntityList.isEmpty())
            return boardDTOList;

        for(BoardEntity boardEntity : boardEntityList){
            boardDTOList.add(this.convertEntityToDto(boardEntity));
        }

        return boardDTOList;
    }

    private BoardDTO convertEntityToDto(BoardEntity boardEntity) {
        return BoardDTO.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];

        Double postsTotalCount = Double.valueOf(this.getBoardCount());

        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));

        Integer blockLastPageNum = (totalLastPageNum>curPageNum+BLOCK_PAGE_NUM_COUNT)
                ? curPageNum + BLOCK_PAGE_NUM_COUNT
                : totalLastPageNum;

        curPageNum = (curPageNum<=3) ? 1 : curPageNum-2;

        for( int val=curPageNum, i=0; val<=blockLastPageNum; val++, i++){
            pageList[i] = val;
        }

        return pageList;

    }

    private Long getBoardCount() {
        return boardRepository.count();
    }

    public List<BoardEntity> getAllPosts() {
        List<BoardEntity> posts = boardRepository.findAll();
//        List<BoardDTO> postDTOs = new ArrayList<>();
//        for(BoardEntity boardEntity:posts){
//            if (boardEntity != null) { // Null 체크 추가
//                postDTOs.add(convertEntityToDto(boardEntity));
//            }
//
//        }
        return posts;
    }

    public List<BoardEntity> getAllBoards() {
        List<BoardEntity> posts = boardRepository.findAll();
        return posts;
    }

    public void saveBoard(BoardEntity boardEntity, User user) {
        boardEntity.setWriter(user.getName());
        boardRepository.save(boardEntity);
    }

    public boolean checkUser(Long id, User user) {
        BoardEntity boardEntity = boardRepository.findById(id).get();
        System.out.println(boardEntity.getWriter());
        System.out.println(user.getName());
        if(boardEntity.getWriter().equals(user.getName())){
            return true;
        }else{
            return false;
        }
    }

    public BoardEntity updateBoard(Long boardId,BoardEntity boardDetails, User user) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElse(null);
        if(boardEntity!=null){
            if(boardEntity.getWriter().equals(user.getName())){
                boardEntity.setTitle(boardDetails.getTitle());
                boardEntity.setContent(boardDetails.getContent());
                BoardEntity updatedBoard= boardRepository.save(boardEntity);
                return updatedBoard;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    public void deleteBoard(Long boardId, User user) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElse(null);
        if (boardEntity != null) {
            if (boardEntity.getWriter().equals(user.getName())) {
                boardRepository.deleteById(boardId);
            } else {
                System.out.println("게시글아이디 유저와 토큰 유저가 다릅니다");
            }
        } else {
            System.out.println("삭제할 게시글이 존재하지 않습니다");
        }
    }

    public List<BoardEntity> getMyBoards(User user) {
        String username= user.getName();
        List<BoardEntity> posts = boardRepository.findByWriter(username);
        return posts;
    }

    public String getBoardtitle(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElse(null);
        String boardTitle=boardEntity.getTitle();
        return boardTitle;
    }
}
