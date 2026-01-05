package com.example.bcsd.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "board")
public class Board {
    @Id
    private Long id;
    private String title; // 게시판 이름

    // 부모 선언 (+ 양방향 매핑(역방향 조회 편의성↑))
    @OneToMany(mappedBy = "board",     // 연관관계의 주인은 Article.board
            cascade = CascadeType.ALL, // 부모의 영속성을 자식(Article)에게도
            orphanRemoval = true)      // 고아 객체 제거 옵션 활성화
    private List<Article> articles = new ArrayList<>();

    public Board(){}
    public Board(String title) {
        this.title = title;
    }
}
