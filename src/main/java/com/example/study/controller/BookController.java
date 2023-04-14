package com.example.study.controller;

import com.example.study.dto.BookResponseDto;
import com.example.study.dto.CreateBookRequestDto;
import com.example.study.service.BookService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/books")
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody CreateBookRequestDto req) {
        return new ResponseEntity<>(bookService.createBook(req), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> findAll() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
    }
}
