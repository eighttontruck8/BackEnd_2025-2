package com.example.bcsd.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. 조회 예외처리 - 존재하지 않는 게시물 조회 시 404
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleEmptyResult(EmptyResultDataAccessException ex) {
//         ex.printStackTrace();
         return ResponseEntity.status(404).body("해당 데이터를 찾을 수 없습니다.");
    }
}
