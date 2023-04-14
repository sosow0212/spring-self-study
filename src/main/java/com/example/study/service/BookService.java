package com.example.study.service;

import com.example.study.dao.BookDao;
import com.example.study.dao.BookDaoImpl;
import com.example.study.domain.Book;
import com.example.study.dto.BookResponseDto;
import com.example.study.dto.CreateBookRequestDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookDao bookDao;

    public BookService(final BookDaoImpl bookDaoImpl) {
        this.bookDao = bookDaoImpl;
    }

    public BookResponseDto createBook(final CreateBookRequestDto req) {
        Book book = Book.from(req);
        bookDao.saveBook(req);
        return BookResponseDto.from(book);
    }

    public List<BookResponseDto> findAll() {
        return bookDao.findAll();
    }

    public BookResponseDto findById(final Long id) {
        return bookDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없다."));
    }

    public void deleteById(final Long id) {
        bookDao.deleteById(id);
    }
}
