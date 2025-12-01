package com.example.bcsd.repository;

import com.example.bcsd.domain.Article;

import java.util.List;

public interface ArticleRepository {
    // 1. GET(조회)
    // b. boardId로 게시판의 게시물들을 JSON 배열로 반환
    // /articles?boardId={boardId}
    List<Article> findByBoardId(Long boardId);

    // c. article의 id로 해당 article 하나 조회
    Article findById(Long id); //ID로 회원 정보 가져오기

    // 2. POST(등록)
    Article insert(Article article);

    // 3. PUT(수정)
    Article update(Article article);

//    // 4. DELETE(삭제)
//    void deleteById(Long id);
}
