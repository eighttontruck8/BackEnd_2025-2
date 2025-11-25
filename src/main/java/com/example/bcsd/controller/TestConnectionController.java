package com.example.bcsd.controller;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConnectionController {

    private final JdbcTemplate jdbcTemplate;

    public TestConnectionController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/test-db")
    public String testDb() {
        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        return "DB 연결 성공: " + result;
    }
}
