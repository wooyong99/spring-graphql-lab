package com.example.graphqldemo.global.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final SecurityService securityService;

    public JwtFilter(JwtProvider jwtProvider, SecurityService securityService){
        this.jwtProvider = jwtProvider;
        this.securityService = securityService;
    }
/*
    JWT 필터 시나리오
    1. JWT 토큰 추출
    2. 토큰 유효성 검사
        2-1. 토큰 null 검사
        2-2. 파싱 검사


*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtProvider.resolveToken(request);

        if(jwtProvider.validationToken(token)) {
            String userId = jwtProvider.getUsername(token);
            Authentication authentication = securityService.getAuthentication(userId);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
