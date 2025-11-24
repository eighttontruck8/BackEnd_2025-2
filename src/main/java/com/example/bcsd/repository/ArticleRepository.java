package com.example.bcsd.repository;

import com.example.bcsd.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    // 1. GET(조회)
    Optional<Article> findById(Long id); //ID로 회원 정보 가져오기
    List<Article> findAll();
    // 2. POST(등록) + 3. PUT(수정)
    Article save(Article article);

    // 4. DELETE(삭제)
    void deleteById(Long id);
}
