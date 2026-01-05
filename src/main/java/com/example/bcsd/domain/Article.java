package com.example.bcsd.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Member author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Article(){}
    public Article(Member author, Board board, String title, String content) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.board = board;
    }
}
