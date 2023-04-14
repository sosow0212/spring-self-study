package com.example.study.dto;

import com.example.study.domain.Book;

public class BookResponseDto {

    private final String title;
    private final String content;

    private BookResponseDto(final String title, final String content) {
        this.title = title;
        this.content = content;
    }

    public static BookResponseDto from(final Book book) {
        return new BookResponseDto(book.getTitle(), book.getContent());
    }

    public static BookResponseDto from(final String title, final String content) {
        return new BookResponseDto(title, content);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
