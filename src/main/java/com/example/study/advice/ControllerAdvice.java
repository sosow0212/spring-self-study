package com.example.study.advice;

import com.example.study.dto.response.Response;
import com.example.study.exception.BookNotfoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(BookNotfoundException.class)
    public ResponseEntity<Response> boardNotFoundException() {
        return new ResponseEntity<>(Response.failure(404, "게시글을 찾을 수 없습니다."), HttpStatus.NOT_FOUND);
    }
}
