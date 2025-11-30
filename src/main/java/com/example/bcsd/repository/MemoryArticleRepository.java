package com.example.bcsd.repository;

import com.example.bcsd.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryArticleRepository implements ArticleRepository {
    // db 연결
    private final JdbcTemplate jdbctemplate; // jdbc 정의
    public MemoryArticleRepository(JdbcTemplate jdbctemplate) {
        this.jdbctemplate = jdbctemplate;
    }

    // 1. GET(조회)

    // b. boardId로 게시판의 게시물들을 JSON 배열로 반환
    // /articles?boardId={boardId}
    public List<Article> findByBoardId(Long board_id){
        String sql = "select * from article where board_id = ?";
        return jdbctemplate.query(sql, new ArticleRowMapper(), board_id);
    };

    // c. article의 id로 해당 article 하나 조회
    // /articles/{id}
    @Override
    public Article findById(Long id){  //ID로 회원 정보 가져오기
        String sql = "select * from article where id = ?";
        return jdbctemplate.queryForObject(sql, new ArticleRowMapper(), id);
    }

//    // 2. POST(등록) + 3. PUT(수정)
//    @Override
//    public Article save(Article article){
//        article.setId(++sequence); // id값 설정
//        articles.put(article.getId(), article); //id값+member 합치기
//        return article;
//    }
//
//    // 4. DELETE(삭제)
//    public void deleteById(Long id){
//        articles.remove(id);
//    }
}
