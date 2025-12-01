package com.example.bcsd.service;

import com.example.bcsd.domain.Article;
import com.example.bcsd.dto.ArticleDTO;

import com.example.bcsd.exception.InvalidReferenceException;
import com.example.bcsd.repository.ArticleRepository;
import com.example.bcsd.repository.MemoryArticleRepository;
import com.example.bcsd.repository.MemoryMemberRepository;
import com.example.bcsd.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final MemoryArticleRepository articleRepository;
    private final MemoryMemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public ArticleService(MemoryArticleRepository articleRepository,
                          MemoryMemberRepository memberRepository,
                          BoardRepository boardRepository) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    // 1. READ - board의 전체 article
    public List<Article> getAllArticlesByBoard(Long boardId){
        return articleRepository.findByBoardId(boardId);
    }
    // 1. READ - 하나만
    public Article getOne(Long id){
        return articleRepository.findById(id);
    }
    // 2. CREATE
    public Article create(Article article){
        return articleRepository.insert(article);
    }
    // 3. UPDATE
    public Article update(ArticleDTO req, Long id){
        if (!memberRepository.existsById(req.getAuthorId())) {
            throw new InvalidReferenceException("존재하지 않는 사용자입니다. author_id: " + req.getAuthorId());
        }

        if (!boardRepository.existsById(req.getBoardId())) {
            throw new InvalidReferenceException("존재하지 않는 게시판입니다. board_id: " + req.getBoardId());
        }

        Article article = articleRepository.findById(id);

        article.setTitle(req.getTitle());
        article.setContent(req.getContent());
        article.setBoardId(req.getBoardId());
        article.setAuthorId(req.getAuthorId());

        return articleRepository.update(article);
    }
    // 4. DELETE
    public boolean delete(Long id){
        return articleRepository.deleteById(id);
    }
}
