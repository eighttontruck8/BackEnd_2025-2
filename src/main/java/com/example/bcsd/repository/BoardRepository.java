package com.example.bcsd.repository;

import com.example.bcsd.domain.Board;
import com.example.bcsd.dto.BoardDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Objects;

@Repository
public class BoardRepository {

    private final JdbcTemplate jdbctemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbctemplate = jdbcTemplate;
    }

    public Board findById(Long id){
        String sql = "select * from board where id = ?";
        return jdbctemplate.queryForObject(sql, new BoardRowMapper(), id);
    }

    // 1. 생성
    public Board insert(BoardDTO board) {
        String sql = "INSERT INTO board (title) VALUES (?)";
        // 자동 증가 PK(id)를 받기 위한 객체 KEYHOLDER
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbctemplate.update(con-> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, board.getTitle());
            return ps;
        }, keyHolder);
        Long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(id);
    }

    // 2. 삭제
    public boolean delete(Long id){
        String sql = "delete from board where id = ?";
        int rows = jdbctemplate.update(sql, id);

        if (rows == 0) {
            throw new IllegalArgumentException(id + "번 게시판이 없습니다.");
        }
        return false;
    }

    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM board WHERE id = ?";
        Integer count = jdbctemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
