package com.example.graphqldemo.global.security;

import com.example.graphqldemo.domain.author.entity.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtils {

    public static boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    public static Long getAuthorId() {
        Member member = getAuthor();
        return member == null ? null : member.getId();
    }

    public static Member getAuthor() {
        return SecurityContextHolder.getContext().getAuthentication() != null ?
                ((Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal()) :
                null;
    }
}
