package com.example.bcsd.repository;
import com.example.bcsd.domain.Board;
import com.example.bcsd.dto.BoardDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
//    Board findById(Long id);

    // 1. 생성
    Board insert(BoardDTO board);

    // 2. 삭제
    boolean delete(Long id);

    boolean existsById(Long id);
}
