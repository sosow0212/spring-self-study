package com.example.study.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Response {

    private final boolean success;
    private final int code;
    private final Result result;

    public Response(final boolean success, final int code, final Result result) {
        this.success = success;
        this.code = code;
        this.result = result;
    }

    public static Response success() {
        return new Response(true, 0, null);
    }

    public static <T> Response success(final T data) {
        return new Response(true, 0, new Success<>(data));
    }

    public static Response failure(final int code, final String msg) {
        return new Response(false, code, new Failure(msg));
    }

    public boolean isSuccess() {
        return success;
    }

    public Result getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }
}
