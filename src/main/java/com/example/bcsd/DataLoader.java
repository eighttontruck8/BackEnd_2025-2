package com.example.bcsd;

import com.example.bcsd.domain.Article;
import com.example.bcsd.repository.ArticleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader {

    private final ArticleRepository articleRepository;

    public DataLoader(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @PostConstruct
    public void init() {
        Article sample1 = new Article();

        sample1.setTitle("제목1");
        sample1.setAuthor("회원1");
        sample1.setCreatedAt(LocalDateTime.now());
        sample1.setContent("");
        articleRepository.save(sample1);

        Article sample2 = new Article();
        sample2.setTitle("제목22");
        sample2.setAuthor("회원1");
        sample2.setCreatedAt(LocalDateTime.now());
        sample2.setContent("내용입니다~~~~~");
        articleRepository.save(sample2);

        Article sample3 = new Article();
        sample3.setTitle("제목333");
        sample3.setAuthor("회원333333");
        sample3.setCreatedAt(LocalDateTime.now());
        sample3.setContent("내용3입니다~!!!!!~~~~");
        articleRepository.save(sample3);
    }
}
