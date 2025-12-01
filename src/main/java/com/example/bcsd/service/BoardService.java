package com.example.bcsd.service;
import com.example.bcsd.domain.Board;
import com.example.bcsd.dto.BoardDTO;
import com.example.bcsd.exception.MissingFieldException;
import com.example.bcsd.exception.RemainArticlesException;
import com.example.bcsd.repository.BoardRepository;
import com.example.bcsd.repository.MemoryArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemoryArticleRepository articleRepository;

    public BoardService(BoardRepository boardRepository,  MemoryArticleRepository articleRepository) {
        this.boardRepository=boardRepository;
        this.articleRepository=articleRepository;
    }

    public Board create(BoardDTO req) {
        validateForCreate(req);
        return boardRepository.insert(req);
    }

    public boolean delete(Long id) {
        if (articleRepository.existsByAuthorId(id)){
            throw new RemainArticlesException("게시판에 작성한 게시글이 남아있어 삭제가 불가능합니다. board_id =" + id);
        }
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
