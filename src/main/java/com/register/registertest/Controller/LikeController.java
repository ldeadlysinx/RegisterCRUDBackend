package com.register.registertest.Controller;

import com.register.registertest.config.auth.PrincipalDetails;
import com.register.registertest.entity.CommentEntity;
import com.register.registertest.entity.Like;
import com.register.registertest.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private final LikeService likeService;



    @GetMapping("/check/{boardId}")
    public boolean CheckComment(@PathVariable Long boardId,@AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean check =likeService.checkUser(boardId,principalDetails.getUser());
        return check;
    }

    @GetMapping("/Get/Count/{boardId}")
    public long getLikeCountByBoard(@PathVariable Long boardId){
        long count = likeService.getLikeCountBoard(boardId);
        return count;
    }

    @GetMapping("/Get/MyPage/like")
    public List<Long> getMyPageLike(@AuthenticationPrincipal PrincipalDetails principalDetails){
        List<Like> likes = likeService.getMyLike(principalDetails.getUser());
        List<Long> boardIds = new ArrayList<>();
        for (Like like : likes) {
            boardIds.add(like.getBoardEntity().getId());
        }

        return boardIds;
    }

    @GetMapping("/Get/MyPage/like/Board/{likeId}")
    public Long getBoardIdByLikeId(@PathVariable Long likeId){
        Long boardId= likeService.getBoardIdByMyLike(likeId);
        return boardId;
    }

    @PostMapping("/save/{boardId}")
    public Like save(@PathVariable Long boardId, Like like , @AuthenticationPrincipal PrincipalDetails principalDetails){
        likeService.saveLike(boardId,like,principalDetails.getUser());
        return null;
    }

    @DeleteMapping("/delete/{boardId}")
    public Like delete(@PathVariable Long boardId , @AuthenticationPrincipal PrincipalDetails principalDetails){
        likeService.deleteLike(boardId,principalDetails.getUser());
        return null;
    }

}
