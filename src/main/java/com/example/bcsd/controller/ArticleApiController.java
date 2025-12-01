package com.example.bcsd.controller;

import com.example.bcsd.domain.Article;
import com.example.bcsd.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleApiController {
    private final ArticleService articleService;

    public ArticleApiController(ArticleService articleService) {
        this.articleService = articleService;
    }
    // 1. POST /articles : 게시글 저장하기
    @PostMapping
    public Article create(@RequestBody Article article){ // ??
        return articleService.create(article);
    }

    // 2. GET /articles?boardId={boardId} : 한 게시판의 모든 article 조회하기
    @GetMapping
    public List<Article> getAllArticlesByBoard(Long boardId){
        return articleService.getAllArticlesByBoard(boardId);
    }

    // 3. GET /articles/{id} : 아이디로 article 찾기
    @GetMapping("/{id}")
    public Article getOne(@PathVariable Long id){
        return articleService.getOne(id);
    }

    // 4. PUT : 게시글 수정하기
    @PutMapping("/{id}")
    public Article update(@PathVariable Long id,
                          @RequestBody Article article){
        article.setId(id); // body에 id넣지 않아도 자동 세팅되도록
        return articleService.update(article);
    }

    // 5. DELETE /articles/{id} : id로 삭제하기
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = articleService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build();  // 404
        }
    }
}
