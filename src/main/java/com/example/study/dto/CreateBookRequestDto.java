package com.example.study.dto;

public class CreateBookRequestDto {

    private String title;
    private String content;

    private CreateBookRequestDto() {

    }

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
