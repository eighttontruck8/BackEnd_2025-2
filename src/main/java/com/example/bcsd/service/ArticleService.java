package com.example.bcsd.service;

import com.example.bcsd.domain.Article;
import com.example.bcsd.domain.Board;
import com.example.bcsd.domain.Member;
import com.example.bcsd.dto.ArticleDTO;
import com.example.bcsd.exception.InvalidReferenceException;
import com.example.bcsd.exception.MissingFieldException;
import com.example.bcsd.repository.ArticleRepository;
import com.example.bcsd.repository.MemberRepository;
import com.example.bcsd.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    // 1. READ - board의 전체 article
    public List<Article> getAllArticlesByBoard(Long boardId){
        return articleRepository.findByBoardId(boardId);
    }

    // 1. READ - 하나만
    public Article getOne(Long id){
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "번 게시글이 없습니다."));
    }

    // 2. CREATE
    public Article create(ArticleDTO req){
        validateForCreate(req);
        Member author = memberRepository.findById(req.getAuthorId())
                .orElseThrow(() -> new InvalidReferenceException("존재하지 않는 사용자입니다. author_id: " + req.getAuthorId()));

        Board board = boardRepository.findById(req.getBoardId())
                .orElseThrow(() -> new InvalidReferenceException("존재하지 않는 게시판입니다. board_id: " + req.getBoardId()));

        Article article = new Article();
        article.setTitle(req.getTitle());
        article.setContent(req.getContent());
        article.setAuthor(author);

        boardRepository.save(board);
        return article;
    }

    // 3. UPDATE
    public Article update(ArticleDTO req, Long id){
        if (!memberRepository.existsById(req.getAuthorId())) {
            throw new InvalidReferenceException("존재하지 않는 사용자입니다. author_id: " + req.getAuthorId());
        }

        if (!boardRepository.existsById(req.getBoardId())) {
            throw new InvalidReferenceException("존재하지 않는 게시판입니다. board_id: " + req.getBoardId());
        }

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "번 게시글이 없습니다."));;

        article.setTitle(req.getTitle());
        article.setContent(req.getContent());

        return articleRepository.save(article);
    }

    // 4. DELETE
    public void delete(Long id){
        if (!articleRepository.existsById(id)) {
            throw new IllegalArgumentException(id + "번 게시글이 없습니다.");
        }
        articleRepository.deleteById(id);
    }

    private void validateForCreate(ArticleDTO req) {
        if (req.getTitle() == null || req.getTitle().isBlank()) {
            throw new MissingFieldException("title은 null일 수 없습니다.");
        }
        if (req.getContent() == null || req.getContent().isBlank()) {
            throw new MissingFieldException("content는 null일 수 없습니다.");
        }
        if (req.getAuthorId() == null) {
            throw new MissingFieldException("authorId는 null일 수 없습니다.");
        }
        if (req.getBoardId() == null) {
            throw new MissingFieldException("boardId는 null일 수 없습니다.");
        }
    }
}
