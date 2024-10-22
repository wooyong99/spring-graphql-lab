package com.example.graphqldemo.domain.book.service;

import com.example.graphqldemo.domain.book.entity.Book;
import com.example.graphqldemo.domain.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByAuthorName(String authorName) {
        return bookRepository.findByAuthorName(authorName);
    }
}
