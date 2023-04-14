package com.example.study.dao;

import com.example.study.dto.BookResponseDto;
import com.example.study.dto.CreateBookRequestDto;
import java.util.List;
import java.util.Optional;

public interface BookDao {

    void saveBook(final CreateBookRequestDto request);

    List<BookResponseDto> findAll();

    Optional<BookResponseDto> findById(final Long id);

    void deleteById(final Long id);
}
