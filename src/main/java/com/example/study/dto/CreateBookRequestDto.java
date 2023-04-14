package com.example.study.dto;

public class CreateBookRequestDto {

    private final String title;
    private final String content;

    public CreateBookRequestDto(final String title, final String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
