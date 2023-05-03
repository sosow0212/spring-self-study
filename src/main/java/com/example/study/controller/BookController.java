package com.example.study.controller;

import com.example.study.dto.board.CreateBookRequestDto;
import com.example.study.dto.member.MemberLoginRequestDto;
import com.example.study.dto.response.Response;
import com.example.study.service.BookService;
import com.example.study.service.member.MemberService;
import com.example.study.util.AuthorizationExtractor;
import com.example.study.util.BasicAuthorizationExtractor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/books")
@RestController
public class BookController {

    private final BookService bookService;

    private final MemberService memberService;
    private final AuthorizationExtractor<MemberLoginRequestDto> authorizationExtractor;

    public BookController(final BookService bookService, final MemberService memberService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.authorizationExtractor = new BasicAuthorizationExtractor();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response createBook(@RequestBody CreateBookRequestDto req) {
        return Response.success(bookService.createBook(req));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response findAll(@RequestHeader("Authorization") final String authHeaderValue) {

        // 1. 헤더에서 로그인 정보를 추출한 후에 로그인 정보가 DB에 있는지 확인한다. (없다면 예외!)
        MemberLoginRequestDto memberLoginRequestDto = authorizationExtractor.extractHeader(authHeaderValue);
        memberService.handleLogin(memberLoginRequestDto);

        // 2. 로그인 정보가 유효하다면 로직을 실행시킨다.
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
