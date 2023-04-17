package com.example.study.dto.response;

public class Success<T> implements Result {

    public T data;

    public Success(final T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
