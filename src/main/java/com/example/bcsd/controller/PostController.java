package com.example.bcsd.controller;

import com.example.bcsd.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/posts") // /posts?boardId={boardId}
public class PostController {
    private final ArticleService articleService;
    public PostController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public String posts(@RequestParam Long boardId, Model model) {
        model.addAttribute("boardName", boardId);
        model.addAttribute("articles", articleService.getAllArticlesByBoard(boardId));
        return "posts";
    }
}
