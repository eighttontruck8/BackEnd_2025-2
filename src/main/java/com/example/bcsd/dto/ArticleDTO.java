package com.example.bcsd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;


public class ArticleDTO {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
