package com.example.study.controller;

import com.example.study.config.LoginMemberArgumentResolver;
import com.example.study.domain.member.Member;
import com.example.study.dto.board.BookResponseDto;
import com.example.study.dto.board.CreateBookRequestDto;
import com.example.study.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = BookController.class)
@AutoConfigureRestDocs // REST Docs 사용을 명시
public class BookControllerTest {

    @MockBean
    BookService bookService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LoginMemberArgumentResolver loginMemberArgumentResolver;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("책 생성을 한다.")
    void create_book_success() throws Exception {
        // given
        CreateBookRequestDto req = new CreateBookRequestDto("title", "content");

        // when & then
        mockMvc.perform(post("/books")
                        .header("Authorization", "val")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)
                        )
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    @DisplayName("책들을 반환한다.")
    void returns_books() throws Exception {
        // given
        List<BookResponseDto> result = List.of(BookResponseDto.from("title", "content"));
        Member member = Member.from("test@tt.com", "pass1123123!!");
        given(loginMemberArgumentResolver.supportsParameter(any())).willReturn(true);
        given(loginMemberArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(member);
        given(bookService.findAll()).willReturn(result);

        // when & then
        mockMvc.perform(get("/books")
                .header("Authorization", "val")
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("ID를 기준으로 책 한 권을 반환한다.")
    void returns_book_by_id() throws Exception {
        // given
        Long id = 1L;
        BookResponseDto result = BookResponseDto.from("title", "content");

        // when & then
        mockMvc.perform(get("/books/{id}", id)
                .header("Authorization", "val")
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("ID를 기준으로 책 한 권을 삭제한다.")
    void delete_book_by_id() throws Exception {
        // given
        Long id = 1L;
        BookResponseDto result = BookResponseDto.from("title", "content");

        // when & then
        mockMvc.perform(delete("/books/{id}", id)
                .header("Authorization", "val")
        ).andExpect(status().isOk());
    }
}
