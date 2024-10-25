package com.example.graphqldemo.domain.book.service;

import com.example.graphqldemo.domain.author.entity.Author;
import com.example.graphqldemo.domain.author.repository.AuthorRepository;
import com.example.graphqldemo.domain.book.entity.Book;
import com.example.graphqldemo.domain.book.repository.BookRepository;
import com.example.graphqldemo.domain.book.service.request.CreateBookServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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

    public Book create(CreateBookServiceRequest request) {
        Author author = authorRepository.findById(request.getAuthorId());
        Book book = Book.of(request.getTitle(), request.getPublishedDate(), author);
        bookRepository.save(book);

        return book;
    }
}
