package com.example.bcsd.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "board")
public class Board {
    @Id
    private Long id;
    private String title; // 게시판 이름
}
