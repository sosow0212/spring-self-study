package com.example.study.config.filter;

import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/"};

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        String requestURI = httpReq.getRequestURI();

        try {
            if (isLoginCheckPath(requestURI)) {
                if (httpReq.getHeader("Authorization") == null) {
                    System.out.println("미인증 사용자 요청 " + requestURI);
                    return;
                }
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            System.out.println("필터 끝~");
        }
    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
