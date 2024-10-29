package com.example.graphqldemo.global.security;

import org.springframework.security.core.Authentication;

public interface SecurityService {

    Authentication getAuthentication(String userId);
}
