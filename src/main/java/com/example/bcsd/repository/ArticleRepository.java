package com.example.bcsd.repository;

import com.example.bcsd.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    // 1. GET(조회)
    // a. boardId로 html 뷰 반환
    // /posts?boardId={boardId}

    // b. boardId로 게시판의 게시물들을 JSON 배열로 반환
    // /articles?boardId={boardId}
    Article findByBoardId(Long id);

    // c. article의 id로 해당 article 하나 조회
    Optional<Article> findById(Long id); //ID로 회원 정보 가져오기

    // 2. POST(등록) + 3. PUT(수정)
    Article save(Article article);

    // 4. DELETE(삭제)
    void deleteById(Long id);
}
