package com.example.bcsd.controller;

import com.example.bcsd.domain.Board;
import com.example.bcsd.dto.BoardDTO;
import com.example.bcsd.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 1. POST /board : 게시판 신규 생성하기
    @PostMapping
    public Board create(@RequestBody BoardDTO req){
        return boardService.create(req);
    }

    // 2. DELETE /boardId/{id} : 게시판 id로 삭제하기
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
