package com.example.bcsd.domain;

import java.time.LocalDateTime;

public class Article {
    private long id;
    private long authorId;
    private long boardId;
    private String title;
    private String content;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Article(){}
}
