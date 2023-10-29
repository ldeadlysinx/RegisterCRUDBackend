package com.register.registertest.Controller;

import com.register.registertest.config.auth.PrincipalDetails;
import com.register.registertest.entity.BoardEntity;
import com.register.registertest.entity.CommentEntity;
import com.register.registertest.repository.CommentRepository;
import com.register.registertest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentTestController {
    private final CommentService commentService;

    public CommentTestController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 게시글에 해당하는 댓글 리스트 조회
    @GetMapping("/board/{boardId}")
    public List<CommentEntity> getCommentsByBoardId(@PathVariable Long boardId) {
        return commentService.getCommentsByBoardId(boardId);
    }

    @GetMapping("/{commentId}/check")
    public boolean CheckComment(@PathVariable Long commentId,@AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean check = commentService.checkUser(commentId,principalDetails.getUser());
        return check;
    }

    @GetMapping("/Get/Count/{boardId}")
    public long getCommentCountByBoard(@PathVariable Long boardId){
        long count = commentService.getCommentCountBoard(boardId);
        return count;
    }

    @PostMapping("/save/{boardId}")
    public CommentEntity save(@PathVariable Long boardId,@RequestBody CommentEntity commentEntity, @AuthenticationPrincipal PrincipalDetails principalDetails){
        commentService.saveComment(boardId,commentEntity,principalDetails.getUser());
        return null;
    }

    @DeleteMapping("/delete/{commentId}")
    public CommentEntity delete(@PathVariable Long commentId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        commentService.deleteComment(commentId,principalDetails.getUser());
        return null;
    }

    @GetMapping("/Get/MyPage/Comment")
    public List<CommentEntity> getMyPageBoard(@AuthenticationPrincipal PrincipalDetails principalDetails){
        List<CommentEntity> comments= commentService.getMyComments(principalDetails.getUser());
        return comments;
    }

    @GetMapping("/Get/MyPage/Comment/Board/{commentId}")
    public Long getBoardIdByCommentId(@PathVariable Long commentId){

        Long boardId = commentService.getBoardIdCommentId(commentId);
        return boardId;

    }
}
