package com.example.bcsd.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String findNameById(Long id) {
        String sql = "SELECT name FROM board WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }
}
