package com.example.bcsd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticleDTO {
    private String title;
    private String content;
    @JsonProperty("board_id")
    private Long boardId;

    @JsonProperty("author_id")
    private Long authorId;

    // getter와 setter
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }
}
