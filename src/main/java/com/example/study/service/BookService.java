package com.example.study.service;

import com.example.study.dao.BookDao;
import com.example.study.dao.BookDaoImpl;
import com.example.study.domain.Book;
import com.example.study.dto.board.BookResponseDto;
import com.example.study.dto.board.CreateBookRequestDto;
import com.example.study.exception.BookNotfoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookDao bookDao;

    public BookService(final BookDaoImpl bookDaoImpl) {
        this.bookDao = bookDaoImpl;
    }

    @Transactional
    public BookResponseDto createBook(final CreateBookRequestDto req) {
        Book book = Book.from(req);
        bookDao.saveBook(req);
        return BookResponseDto.from(book);
    }

    @Transactional(readOnly = true)
    public List<BookResponseDto> findAll() {
        return bookDao.findAll();
    }

    @Transactional(readOnly = true)
    public BookResponseDto findById(final Long id) {
        Book book = bookDao.findById(id)
                .orElseThrow(BookNotfoundException::new);

        return BookResponseDto.from(book);
    }

    @Transactional
    public void deleteById(final Long id) {
        bookDao.deleteById(id);
    }
}
