package com.example.graphqldemo.global.security;

import com.example.graphqldemo.domain.author.entity.Member;
import com.example.graphqldemo.domain.author.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class SecurityServiceImpl implements SecurityService {

    private final MemberRepository memberRepository;

    @Autowired
    public SecurityServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Authentication getAuthentication(String authorId) {
        Member member = getAuthor(authorId);
        return createAuthentication(member);
    }

    private Member getAuthor(String authorId) {
        long id = Long.parseLong(authorId);

        return memberRepository.findById(id);
    }

    private UsernamePasswordAuthenticationToken createAuthentication(Member member){
        return new CustomAuthentication(member);
    }
}
