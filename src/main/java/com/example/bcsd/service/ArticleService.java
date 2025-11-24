package com.example.bcsd.service;

import com.example.bcsd.domain.Article;
import com.example.bcsd.repository.MemoryArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private MemoryArticleRepository articleRepository;

    // 1. CREATE
    public Article create(Article article){
        // ★★★★★ sequence 할당해주기x repo에 함수 다 만들어뒀으니 갖다 쓰기만 하면됨!!!!
        return articleRepository.save(article);
    }
    // 2. READ - 전체 article
    public List<Article> getAll(){
        return articleRepository.findAll();
    }
    // 2. READ - 하나만
    public Article getOne(Long id){
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("article not found: " + id));
    }
    // 3. UPDATE
    public Article update(Article article){
        return articleRepository.save(article);
    }
    // 4. DELETE
    public void delete(Long id){
        articleRepository.deleteById(id);
    }
}
