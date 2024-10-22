package com.example.graphqldemo.domain.book.repository;

import com.example.graphqldemo.domain.book.entity.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findByAuthorName(String authorName);

    List<Book> findAll();

    Book findById(Long bookId);
}
