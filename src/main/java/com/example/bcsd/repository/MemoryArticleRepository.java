package com.example.bcsd.repository;

import com.example.bcsd.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.*;

@Repository
public class MemoryArticleRepository implements ArticleRepository {
    // db 연결
    private final JdbcTemplate jdbctemplate; // jdbc 정의
    public MemoryArticleRepository(JdbcTemplate jdbctemplate) {
        this.jdbctemplate = jdbctemplate;
    }

    // 1. GET(조회)

    // a&b. boardId로 게시판의 게시물들을 모두 JSON 배열로 반환
    // /articles?boardId={boardId}
    public List<Article> findByBoardId(Long board_id){
        String sql = "select * from article where board_id = ?";
        return jdbctemplate.query(sql, new ArticleRowMapper(), board_id);
    }

    // c. article의 id로 해당 article 하나 조회
    // /articles/{id}
    @Override
    public Article findById(Long id){  //ID로 회원 정보 가져오기
        String sql = "select * from article where id = ?";
        return jdbctemplate.queryForObject(sql, new ArticleRowMapper(), id);
    }

    // 2. POST(등록)
    @Override
    public Article insert(Article article){
        String sql = "INSERT INTO article(board_id, author_id, title, content)" +
                     "VALUES (?, ?, ?, ?)";

        // 자동 증가 PK(id)를 받기 위한 객체 KEYHOLDER
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbctemplate.update(con-> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, article.getBoardId());
            ps.setLong(2, article.getAuthorId());
            ps.setString(3, article.getTitle());
            ps.setString(4, article.getContent());
            return ps;
        }, keyHolder);

        // 방금 insert된 row의 id 가져오기 + NULL 처리
        Long id = Objects.requireNonNull(keyHolder.getKey()).longValue();

        // date까지 채워진 상태를 다시 조회해서 리턴해주기.
        return findById(id);
    }
    // 3. PUT(수정)
    @Override
    public Article update(Article article){
        String sql = "UPDATE article SET title = ?, content = ?, author_id = ?, board_id = ? WHERE id = ?";
        jdbctemplate.update(sql,
                article.getTitle(),
                article.getContent(),
                article.getAuthorId(),
                article.getBoardId(),
                article.getId());
        return findById(article.getId());
    }
    // 4. DELETE(삭제)
    public boolean deleteById(Long id){
        String sql = "DELETE FROM article WHERE id = ?";
        int rows = jdbctemplate.update(sql, id);

        if (rows == 0) {
            throw new IllegalArgumentException(id + "번 게시글이 없습니다.");
        }
        return false;
    }
    public boolean existsByAuthorId(Long authorId){
        String sql = "select count(*) from article where author_id = ?";
        int count = jdbctemplate.queryForObject(sql, new Object[]{authorId}, Integer.class);
        return count > 0;
    }
    public boolean existsByBoardId(Long boardId){
        String sql = "select count(*) from board where board_id = ?";
        int count = jdbctemplate.queryForObject(sql, new Object[]{boardId}, Integer.class);
        return count > 0;
    }
}
