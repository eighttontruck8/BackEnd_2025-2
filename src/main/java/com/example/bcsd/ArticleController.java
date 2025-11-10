package com.example.bcsd;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/articles") //getMapping, PostMapping, PutMapping, DeleteMapping 전부 포함!
public class ArticleController {

    // data가 들어있는 객체 map 생성. key-value 구조(Java의 딕셔너리 격)
    private Map<Integer, Article> articles = new HashMap<>();
    private int nextId = 1;

    static class Article {
        public int id;
        public String title;
        public String content;

        public Article() {} // JSON 파싱용 기본 생성자

        // article은 id(글 번호), title(글 제목), content(글 내용)으로 이루어짐
        // new 사용해서 새로 생성하지 않는 것이 순수 java와의 차이점임!
        public Article(int id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }
    }
    // Create (POST)
    @PostMapping
    public ResponseEntity<Article> create(@RequestBody Article article) {
        article.id = nextId++;
        articles.put(article.id, article);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    // Read (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Article> read(@PathVariable int id) {
        Article article = articles.get(id);
        return ResponseEntity.ok(article);
    }

    // Update (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable int id, @RequestBody Article updated) {
        Article old = articles.get(id);
        if (old == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        old.title = updated.title;
        old.content = updated.content;
        return ResponseEntity.ok(old);
    }

    // Delete (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        if (!articles.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found");
        }
        articles.remove(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted article " + id);
    }
}
