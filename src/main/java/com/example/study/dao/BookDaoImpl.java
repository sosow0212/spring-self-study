package com.example.study.dao;

import com.example.study.domain.Book;
import com.example.study.dto.board.BookResponseDto;
import com.example.study.dto.board.CreateBookRequestDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveBook(final CreateBookRequestDto request) {
        String sql = "INSERT INTO book (title, content) VALUES (?, ?)";

        jdbcTemplate.update(sql,
                request.getTitle(),
                request.getContent()
        );
    }

    @Override
    public List<BookResponseDto> findAll() {
        String sql = "SELECT * FROM book";
        List<BookResponseDto> result = new ArrayList<>();

        jdbcTemplate.query(sql, res -> {
            String title = res.getString("title");
            String content = res.getString("content");
            result.add(BookResponseDto.from(title, content));
        });

        return result;
    }

    @Override
    public Optional<Book> findById(final Long id) {
        String sql = "SELECT title, content FROM book WHERE id = ?";
        Object[] args = new Object[]{id};

        List<Book> books = jdbcTemplate.query(sql, args, (res, rowNum) -> {
            String title = res.getString("title");
            String content = res.getString("content");
            return Book.from(title, content);
        });

        if (books.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(books.get(0));
    }

    @Override
    public void deleteById(final Long id) {
        String sql = "DELETE FROM book WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
