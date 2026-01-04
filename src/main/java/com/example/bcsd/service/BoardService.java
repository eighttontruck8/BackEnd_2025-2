package com.example.bcsd.service;
import com.example.bcsd.domain.Board;
import com.example.bcsd.dto.BoardDTO;
import com.example.bcsd.exception.MissingFieldException;
import com.example.bcsd.exception.RemainArticlesException;
import com.example.bcsd.repository.BoardRepository;
import com.example.bcsd.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final ArticleRepository articleRepository;

    public BoardService(BoardRepository boardRepository, ArticleRepository articleRepository) {
        this.boardRepository = boardRepository;
        this.articleRepository = articleRepository;
    }

    public Board create(BoardDTO req) {
        validateForCreate(req);

        // dto -> entity로 변환
        Board board = new Board();
        board.setTitle(req.getTitle());

        return boardRepository.save(board);
    }

    public void delete(Long id) {
        if (articleRepository.existsByBoardId(id)){
            throw new RemainArticlesException("게시판에 작성한 게시글이 남아있어 삭제가 불가능합니다. board_id =" + id);
        }
        boardRepository.deleteById(id);
    }

    private void validateForCreate(BoardDTO req) {

        if (req.getTitle() == null || req.getTitle().isBlank()) {
            throw new MissingFieldException("title은 null일 수 없습니다.");
        }
    }

    public String getBoardName(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException(boardId + "번 게시판이 없습니다."));
        return board.getTitle();
    }
}
