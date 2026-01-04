package com.example.bcsd.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "article")
public class Article {
    @Id
    private Long id;
    @JsonProperty("author_id")
    private Long authorId;

    @JsonProperty("board_id")
    private Long boardId;

    private String title;
    private String content;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Article(){}
}
