package com.example.graphqldemo.domain.book.controller;

import com.example.graphqldemo.domain.book.controller.request.CreateBookRequest;
import com.example.graphqldemo.domain.book.entity.Book;
import com.example.graphqldemo.domain.book.service.BookService;
import com.example.graphqldemo.domain.book.service.request.CreateBookServiceRequest;
import com.example.graphqldemo.global.security.SecurityUtils;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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
        return null;
    }

    @MutationMapping
    public Book addBook(@Arguments CreateBookRequest request){
        Long authorId = SecurityUtils.getAuthorId();
        CreateBookServiceRequest serviceRequest = new CreateBookServiceRequest(authorId, request.getTitle(), request.getPublishedDate());
        return bookService.create(serviceRequest);
    }
}
