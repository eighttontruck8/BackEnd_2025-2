package com.example.bcsd.repository;

import com.example.bcsd.domain.Board;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardRowMapper implements RowMapper<Board> {
    @Override
    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
        Board board = new Board();

        // setter
        board.setId(rs.getLong("id"));
        board.setTitle(rs.getString("title"));

        return board;
    }
}
