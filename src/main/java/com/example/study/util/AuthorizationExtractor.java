package com.example.study.util;


public interface AuthorizationExtractor<T> {

    T extractHeader(String request);
}
