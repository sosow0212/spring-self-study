package com.example.study.controller;

import com.example.study.dto.board.CreateBookRequestDto;
import com.example.study.dto.response.Response;
import com.example.study.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/books")
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response createBook(@RequestBody CreateBookRequestDto req) {
        return Response.success(bookService.createBook(req));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response findAll() {
        return Response.success(bookService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response findById(@PathVariable Long id) {
        return Response.success(bookService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return Response.success("삭제 성공");
    }
}
