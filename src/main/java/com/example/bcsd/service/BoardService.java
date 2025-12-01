package com.example.bcsd.service;
import com.example.bcsd.domain.Board;
import com.example.bcsd.dto.BoardDTO;
import com.example.bcsd.exception.MissingFieldException;
import com.example.bcsd.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    public BoardService(BoardRepository boardRepository){
        this.boardRepository=boardRepository;
    }

    public Board create(BoardDTO req) {
        validateForCreate(req);
        return boardRepository.insert(req);
    }

    public boolean delete(Long id) {
        return boardRepository.delete(id);
    }

    private void validateForCreate(BoardDTO req) {

        if (req.getTitle() == null || req.getTitle().isBlank()) {
            throw new MissingFieldException("title은 null일 수 없습니다.");
        }
    }

    public String getBoardName(Long boardId) {
        return boardRepository.findById(boardId).getTitle();
    }
}
