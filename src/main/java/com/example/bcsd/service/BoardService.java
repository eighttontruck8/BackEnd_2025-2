package com.example.bcsd.service;
import com.example.bcsd.domain.Board;
import com.example.bcsd.exception.MissingFieldException;
import com.example.bcsd.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    public BoardService(BoardRepository boardRepository){
        this.boardRepository=boardRepository;
    }

    public String getBoardName(Long boardId) {
        return boardRepository.findNameById(boardId);
    }
    private void validateForCreate(Board board) {

        if (board.getTitle() == null || board.getTitle().isBlank()) {
            throw new MissingFieldException("title은 null일 수 없습니다.");
        }

        if (board.getId() == null) {
            throw new MissingFieldException("Id는 null일 수 없습니다.");
        }
    }
}
