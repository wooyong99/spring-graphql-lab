package com.example.graphqldemo.domain.book.repository;

import com.example.graphqldemo.domain.book.entity.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findByMemberName(String memberName);

    List<Book> findAll();

    Book findById(Long bookId);

    void save(Book book);
}
