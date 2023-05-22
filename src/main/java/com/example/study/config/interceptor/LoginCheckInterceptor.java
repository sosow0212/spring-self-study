package com.example.study.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {

        if (request.getHeader("Authorization") == null) {
            throw new IllegalArgumentException("미인증 사용자입니다 - interceptor.");
        }

        return true;
    }
}
