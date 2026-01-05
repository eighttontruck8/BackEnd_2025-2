package com.example.bcsd.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "ID")
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "author")
    private List<Article> articles = new ArrayList<>();
}
