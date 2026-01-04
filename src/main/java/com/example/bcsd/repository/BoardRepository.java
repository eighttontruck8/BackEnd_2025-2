package com.example.bcsd.repository;
import com.example.bcsd.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
    // 내용 전부 삭제. JPA가 대체.
}
