package com.example.bcsd.dto;

import com.example.bcsd.domain.Article;

public record ArticleResponse(
        Long id,
        String title,
        String content,
        Long boardId,
        Long authorId
) {
    public static ArticleResponse from(Article a) {
        return new ArticleResponse(
                a.getId(),
                a.getTitle(),
                a.getContent(),
                a.getBoard() != null ? a.getBoard().getId() : null,
                a.getAuthor() != null ? a.getAuthor().getId() : null
        );
    }
}
