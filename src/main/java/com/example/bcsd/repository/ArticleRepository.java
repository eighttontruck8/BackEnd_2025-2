package com.example.bcsd.repository;

import com.example.bcsd.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Integer> {

    boolean existsByBoardId(Integer id);
}
