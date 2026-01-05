package com.example.bcsd.repository;

import com.example.bcsd.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    boolean existsByBoardId(Long id);
    boolean existsByAuthorId(Long authorId);
    List<Article> findByBoardId(Long boardId);
}
