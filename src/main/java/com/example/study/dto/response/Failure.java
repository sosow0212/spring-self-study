package com.example.study.dto.response;

public class Failure implements Result {

    private final String msg;

    public Failure(final String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
