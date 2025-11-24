package com.example.bcsd.controller;

import com.example.bcsd.domain.Article;
import com.example.bcsd.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleApiController {
    private final ArticleService articleService;

    public ArticleApiController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 1. GET /articles/{id} : 아이디로 article 찾기
    @GetMapping("/{id}")
    public Article getOne(@PathVariable Long id){
        return articleService.getOne(id);
    }

    // 2. GET /articles : 모든 article 조회하기
    @GetMapping
    public List<Article> getAll(){
        return articleService.getAll();
    }
    // 3. POST /articles : 게시글 저장하기
    @PostMapping("/{id}")
    public Article create(@RequestBody Article article){ // ??
        return articleService.create(article);
    }
    // 4. PUT : 게시글 수정하기
    @PutMapping("/{id}")
    public Article update(Article article){
        return articleService.update(article);
    }
    // 5. DELETE /articles/{id} : id로 삭제하기
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        articleService.delete(id);
    }

}
