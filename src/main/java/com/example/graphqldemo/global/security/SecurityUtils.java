package com.example.graphqldemo.global.security;

import com.example.graphqldemo.domain.author.entity.Author;
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
        Author author = getAuthor();
        return author == null ? null : author.getId();
    }

    public static Author getAuthor() {
        return SecurityContextHolder.getContext().getAuthentication() != null ?
                ((Author) SecurityContextHolder.getContext().getAuthentication().getPrincipal()) :
                null;
    }
}
