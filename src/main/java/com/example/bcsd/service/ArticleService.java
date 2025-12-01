package com.example.bcsd.service;

import com.example.bcsd.domain.Article;
import com.example.bcsd.repository.MemoryArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final MemoryArticleRepository articleRepository;

    public ArticleService(MemoryArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // 1. READ - board의 전체 article
    public List<Article> getAllArticlesByBoard(Long boardId){
        return articleRepository.findByBoardId(boardId);
    }
    // 1. READ - 하나만
    public Article getOne(Long id){
        return articleRepository.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException( id+"번 게시글이 없습니다."));
    }
    // 2. CREATE
    public Article create(Article article){
        return articleRepository.insert(article);
    }
    // 3. UPDATE
    public Article update(Article article){
        return articleRepository.update(article);
    }
    // 4. DELETE
    public boolean delete(Long id){
        return articleRepository.deleteById(id);
    }
}
