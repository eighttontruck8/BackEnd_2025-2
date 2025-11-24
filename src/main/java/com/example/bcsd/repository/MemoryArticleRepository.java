package com.example.bcsd.repository;

import com.example.bcsd.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryArticleRepository implements ArticleRepository {
    // 실제 구현부
    private static final Map<Long, Article> articles = new HashMap<>();
    private static long sequence = 0L;

    // 1. GET(조회)
    @Override
    public Optional<Article> findById(Long id){  //ID로 회원 정보 가져오기
        return Optional.ofNullable(articles.get(id));
    }
    // 1-1. GET(모든 ARTICLE 조회)
    public List<Article> findAll(){
        return new ArrayList<>(articles.values());
    }

    // 2. POST(등록) + 3. PUT(수정)
    @Override
    public Article save(Article article){
        article.setId(++sequence); // id값 설정
        articles.put(article.getId(), article); //id값+member 합치기
        return article;
    }

    // 4. DELETE(삭제)
    public void deleteById(Long id){
        articles.remove(id);
    }
}
