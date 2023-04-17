package com.example.study.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.example.study.dao.BookDaoImpl;
import com.example.study.dto.board.BookResponseDto;
import com.example.study.dto.board.CreateBookRequestDto;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookDaoImpl bookDaoImpl;

    @Test
    @DisplayName("책을 생성한다.")
    void create_book() {
        // given
        CreateBookRequestDto req = new CreateBookRequestDto("title", "content");
        doNothing().when(bookDaoImpl).saveBook(req);

        // when
        BookResponseDto result = bookService.createBook(req);

        // then
        assertThat(result.getTitle()).isEqualTo(req.getTitle());
    }

    @Test
    @DisplayName("책을 모두 반환한다.")
    void returns_all_books() {
        // given
        List<BookResponseDto> expectedResult = List.of(BookResponseDto.from("title", "content"));
        given(bookDaoImpl.findAll()).willReturn(expectedResult);

        // when
        List<BookResponseDto> result = bookService.findAll();

        // then
        assertAll(
                () -> assertThat(result.size()).isEqualTo(1),
                () -> assertThat(result.get(0).getTitle()).isEqualTo(expectedResult.get(0).getTitle())
        );
    }

    @Test
    @DisplayName("책을 한 권 반환한다.")
    void returns_book_by_id() {
        // given
        Long id = 1L;
        BookResponseDto expectedResult = BookResponseDto.from("title", "content");
        given(bookDaoImpl.findById(id)).willReturn(Optional.of(expectedResult));

        // when
        BookResponseDto result = bookService.findById(id);

        // then
        assertThat(result.getTitle()).isEqualTo(expectedResult.getTitle());
    }

    @Test
    @DisplayName("책을 삭제한다.")
    void delete_book_by_id() {
        // given
        Long id = 1L;

        // when
        bookService.deleteById(id);

        // then
        verify(bookDaoImpl).deleteById(id);
    }
}
