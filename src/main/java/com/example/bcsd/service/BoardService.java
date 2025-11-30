package com.example.bcsd.service;
import com.example.bcsd.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private static BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public static String getBoardName(Long boardId) {
        return boardRepository.findNameById(boardId);
    }
}
