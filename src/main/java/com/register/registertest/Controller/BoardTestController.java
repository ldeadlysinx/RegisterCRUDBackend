package com.register.registertest.Controller;


import com.register.registertest.config.auth.PrincipalDetails;
import com.register.registertest.dto.BoardDTO;
import com.register.registertest.entity.BoardEntity;
import com.register.registertest.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/board", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoardTestController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public List<BoardEntity> getAllPosts(){
        List<BoardEntity> posts = boardService.getAllBoards();
        return posts;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> detail(@PathVariable Long id, Model model){
        BoardDTO boardDTO=boardService.getPost(id);
        if (boardDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(boardDTO);
    }



    @GetMapping("/{id}/check")
    public boolean CheckPost(@PathVariable Long id,@AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean check = boardService.checkUser(id,principalDetails.getUser());
        return check;
    }

    @PostMapping("/save")
    public BoardEntity save(@RequestBody BoardEntity boardEntity, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.saveBoard(boardEntity,principalDetails.getUser());
        return null;
    }

    @PutMapping("/update/{boardId}")
    public ResponseEntity<BoardEntity> updatePost(@PathVariable Long boardId,@RequestBody BoardEntity boardDetails ,@AuthenticationPrincipal PrincipalDetails principalDetails) {
      BoardEntity updatedBoard = boardService.updateBoard(boardId,boardDetails,principalDetails.getUser());
      if(updatedBoard!=null){
          return ResponseEntity.ok(updatedBoard);
      }
      else{
          return ResponseEntity.badRequest().body(updatedBoard);
      }
    }

    @DeleteMapping("/delete/{boardId}")
    public BoardEntity delete(@PathVariable Long boardId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boardService.deleteBoard(boardId,principalDetails.getUser());

        return null;
    }

    @GetMapping("/Get/MyPage/Board")
    public List<BoardEntity> getMyPageBoard(@AuthenticationPrincipal PrincipalDetails principalDetails){
        List<BoardEntity> posts = boardService.getMyBoards(principalDetails.getUser());
        return posts;
    }

    @GetMapping("/Get/BoardTitle/{boardId}")
    public String getBoardTitle(@PathVariable Long boardId){
        String BoardTitle = boardService.getBoardtitle(boardId);
        return BoardTitle;
    }
}
