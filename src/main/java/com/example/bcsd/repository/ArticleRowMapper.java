package com.example.bcsd.repository;

import com.example.bcsd.domain.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//public interface RowMapper<T> {
//    T mapRow(ResultSet rs, int rowNum) throws SQLException;
//}
public class ArticleRowMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = new Article();

        // setter
        article.setId(rs.getLong("id"));
        article.setAuthorId(rs.getLong("author"));

        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));

        article.setCreatedAt(rs.getTimestamp("created_date").toLocalDateTime());
        article.setUpdatedAt(rs.getTimestamp("updated_date").toLocalDateTime());
        return article;
    }
}
