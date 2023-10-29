package com.register.registertest.Controller;

import com.register.registertest.dto.BoardDTO;
import com.register.registertest.entity.BoardEntity;
import com.register.registertest.service.BoardService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private final BoardService boardService;

    @GetMapping("/board/list")
    public String list(Model model,@RequestParam(value="page",defaultValue = "1") Integer pageNum){
        List<BoardDTO> boardDTOList = boardService.getBoardList(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList",boardDTOList);
        model.addAttribute("pageList",pageList);

        return "board/list.html";
    }

    @GetMapping("/board/post")
    public String write(){
        return "board/write.html";
    }

    @GetMapping("/board/post/{id}")
    public String detail(@PathVariable Long id,Model model){
        BoardDTO boardDTO=boardService.getPost(id);

        model.addAttribute("boardDto",boardDTO);
        return "board/detail.html";
    }

    @GetMapping("/dashboard")
    public List<BoardEntity> getAllPosts(){
        List<BoardEntity> posts = boardService.getAllPosts();
        return posts;
    }

    @GetMapping("/board/post/edit/{id}")
    public String edit(@PathVariable Long id,Model model){
        BoardDTO boardDTO = boardService.getPost(id);

        model.addAttribute("boardDto",boardDTO);

        return "board/update.html";
    }

    @GetMapping("/board/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model){
        List<BoardDTO> boardDTOList = boardService.searchPosts(keyword);
        model.addAttribute("boardList",boardDTOList);

        return "board/list.html";
    }

    @PostMapping("/board/post")
    public String write(BoardDTO boardDto){
        boardService.savePost(boardDto);
        return "redirect:/board/list";
    }

    @PostMapping("/board/post/edit/{id}")
    public String update(BoardDTO boardDTO){
        boardService.update(boardDTO);
        return "redirect:/board/list";
    }

    @PostMapping("/board/post/delete/{id}")
    public String delete(@PathVariable Long id,Model model){
        boardService.deletePost(id);

        return "redirect:/board/list";
    }


}
