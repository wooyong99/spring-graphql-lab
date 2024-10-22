package com.example.graphqldemo.domain.author.repository;

import com.example.graphqldemo.domain.author.entity.Author;

import java.util.List;

public interface AuthorRepository {
    List<Author> findAll();

    Author findById(Long authorId);
}
