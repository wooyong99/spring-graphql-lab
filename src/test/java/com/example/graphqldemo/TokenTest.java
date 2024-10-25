package com.example.graphqldemo;

import com.example.graphqldemo.global.security.JwtProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TokenTest {

    @Autowired
    private JwtProvider jwtProvider;

    @Test
    void createToken(){
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        System.out.println(jwtProvider.generateToken("1", authorities));
    }
}
