package com.example.graphqldemo.global.security;

import com.example.graphqldemo.domain.author.entity.Author;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
public class CustomAuthentication extends UsernamePasswordAuthenticationToken {

    private Author author;

    public CustomAuthentication(Author author) {
        super(author, null, null);
        this.author = author;
    }

    @Override
    public String getName(){
        return String.valueOf(author.getId());
    }
}
