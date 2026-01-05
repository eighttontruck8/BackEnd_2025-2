package com.example.bcsd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticleDTO {
    private String title;
    private String content;
    @JsonProperty("board_id")
    private Long boardId;

    @JsonProperty("author_id")
    private Long authorId;

}
