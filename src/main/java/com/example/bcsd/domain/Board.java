package com.example.bcsd.domain;

public class Board {
    private long id;
    private String title; // 게시판 이름

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
