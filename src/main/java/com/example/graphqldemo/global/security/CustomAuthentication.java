package com.example.graphqldemo.global.security;

import com.example.graphqldemo.domain.author.entity.Member;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
public class CustomAuthentication extends UsernamePasswordAuthenticationToken {

    private Member member;

    public CustomAuthentication(Member member) {
        super(member, null, null);
        this.member = member;
    }

    @Override
    public String getName(){
        return String.valueOf(member.getId());
    }
}
