package com.example.study.config.interceptor;

import com.example.study.exception.BookNotfoundException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        HttpSession session = request.getSession();

        if (request.getHeader("Authorization") == null) {
            System.out.println("미인증 사용자");
            throw new BookNotfoundException();
//            return false;
        }

        return true;
    }
}
