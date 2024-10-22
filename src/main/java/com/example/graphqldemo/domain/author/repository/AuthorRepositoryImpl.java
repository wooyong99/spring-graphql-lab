package com.example.graphqldemo.domain.author.repository;

import com.example.graphqldemo.domain.author.entity.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    private final AuthorJpaRepository authorJpaRepository;

    public AuthorRepositoryImpl(AuthorJpaRepository authorJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorJpaRepository.findAll();
    }

    @Override
    public Author findById(Long authorId) {
        return authorJpaRepository.findById(authorId).get();
    }
}
