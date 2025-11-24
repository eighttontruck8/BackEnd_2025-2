package com.example.bcsd.controller;

import com.example.bcsd.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final ArticleService articleService;
    public PostController(ArticleService articleService) {
        this.articleService = articleService;
    }
    @GetMapping
    public String posts(Model model) {
        model.addAttribute("boardName", "자유게시판");
        model.addAttribute("articles", articleService.getAll());
        return "posts";
    }
}
