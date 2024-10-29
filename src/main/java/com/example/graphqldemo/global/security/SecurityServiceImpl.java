package com.example.graphqldemo.global.security;

import com.example.graphqldemo.domain.author.entity.Author;
import com.example.graphqldemo.domain.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class SecurityServiceImpl implements SecurityService {

    private final AuthorRepository authorRepository;

    @Autowired
    public SecurityServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Authentication getAuthentication(String authorId) {
        Author author = getAuthor(authorId);
        return createAuthentication(author);
    }

    private Author getAuthor(String authorId) {
        long id = Long.parseLong(authorId);

        return authorRepository.findById(id);
    }

    private UsernamePasswordAuthenticationToken createAuthentication(Author author){
        return new CustomAuthentication(author);
    }
}
