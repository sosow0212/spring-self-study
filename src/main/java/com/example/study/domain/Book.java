package com.example.study.domain;

import com.example.study.dto.CreateBookRequestDto;

public class Book {

    private final String title;
    private final String content;

    private Book(final String title, final String content) {
        this.title = title;
        this.content = content;
    }

    public static Book from(final CreateBookRequestDto req) {
        return new Book(req.getTitle(), req.getContent());
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
