package com.example.bcsd.controller;

import com.example.bcsd.service.ArticleService;
import com.example.bcsd.service.BoardService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/posts") // /posts?boardId={boardId}
public class PostController {
    private final BoardService boardService;
    private final ArticleService articleService;

    public PostController(ArticleService articleService, BoardService boardService) {
        this.boardService = boardService;
        this.articleService = articleService;
    }

    @GetMapping
    public String posts(@RequestParam("boardId") Long boardId, Model model) {
        String boardName = boardService.getBoardName(boardId);
        model.addAttribute("boardName", boardName);
        model.addAttribute("articles", articleService.getAllArticlesByBoard(boardId));
        return "posts";
    }
}
