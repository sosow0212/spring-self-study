package com.example.study.dao;

import com.example.study.domain.Book;
import com.example.study.dto.board.BookResponseDto;
import com.example.study.dto.board.CreateBookRequestDto;
import java.util.List;
import java.util.Optional;

public interface BookDao {

    void saveBook(final CreateBookRequestDto request);

    List<BookResponseDto> findAll();

    Optional<Book> findById(final Long id);

    void deleteById(final Long id);
}
