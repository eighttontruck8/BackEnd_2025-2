package com.example.bcsd.dto;

import com.example.bcsd.domain.Board;

public record BoardResponse(
        Long id,
        String title
) {
    public static BoardResponse from(Board b) {
        return new BoardResponse(b.getId(), b.getTitle());
    }
}
