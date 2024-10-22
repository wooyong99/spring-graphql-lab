package com.example.graphqldemo.domain.book.repository;

import com.example.graphqldemo.domain.book.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final BookJpaRepository bookJpaRepository;

    @Autowired
    public BookRepositoryImpl(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        return bookJpaRepository.findByAuthorName(authorName);
    }

    @Override
    public List<Book> findAll() {
        return bookJpaRepository.findAll();
    }

    @Override
    public Book findById(Long bookId) {
        return bookJpaRepository.findById(bookId).get();
    }
}
