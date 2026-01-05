package com.example.bcsd.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"articles"})
@Setter
@Getter
@Entity
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title; // 게시판 이름

    // 부모 선언 (+ 양방향 매핑(역방향 조회 편의성↑))
    @OneToMany(mappedBy = "board",     // 연관관계의 주인은 Article.board
            cascade = CascadeType.ALL, // 부모의 영속성을 자식(Article)에게도
            orphanRemoval = true)      // 고아 객체 제거 옵션 활성화
    @JsonIgnore
    private List<Article> articles = new ArrayList<>();

    public Board(){}
    public Board(String title) {
        this.title = title;
    }

    // 연관관계 편의 메서드
    public void addArticle(Article article) {
        articles.add(article);
        article.setBoard(this);
    }

    public void removeArticle(Article article) {
        articles.remove(article);
        article.setBoard(null);
    }
}
