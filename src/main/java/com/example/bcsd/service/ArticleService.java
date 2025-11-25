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
//    // 1. CREATE
//    public Article create(Article article){
//        // ★★★★★ sequence 할당해주기x repo에 함수 다 만들어뒀으니 갖다 쓰기만 하면됨!!!!
//        return articleRepository.save(article);
//    }
    // 2. READ - board의 전체 article
    public List<Article> getAllArticlesByBoard(Long boardId){
        return articleRepository.findByBoardId(boardId);
    }
    // 2. READ - 하나만
    public Article getOne(Long id){
        return articleRepository.findById(id);
//                .orElseThrow(() -> new IllegalArgumentException( id+"번 게시글이 없습니다."));
    }
//    // 3. UPDATE
//    public Article update(Article article){
//        return articleRepository.save(article);
//    }
//    // 4. DELETE
//    public void delete(Long id){
//        articleRepository.deleteById(id);
//    }
}
