package com.example.graphqldemo.domain.book.controller;

import com.example.graphqldemo.domain.book.entity.Book;
import com.example.graphqldemo.domain.book.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public Book getBookById(@Argument Long bookId){
        return bookService.getBookById(bookId);
    }

    @QueryMapping
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }

    @QueryMapping
    public List<Book> getBooksByAuthorName(@Argument String authorName){
        return bookService.getBooksByAuthorName();
    }
}
