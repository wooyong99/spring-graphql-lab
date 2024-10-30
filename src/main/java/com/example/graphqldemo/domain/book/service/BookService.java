package com.example.graphqldemo.domain.book.service;

import com.example.graphqldemo.domain.author.entity.Member;
import com.example.graphqldemo.domain.author.repository.MemberRepository;
import com.example.graphqldemo.domain.book.entity.Book;
import com.example.graphqldemo.domain.book.repository.BookRepository;
import com.example.graphqldemo.domain.book.service.request.CreateBookServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    private MemberRepository memberRepository;

    @Autowired
    public BookService(BookRepository bookRepository, MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByAuthorName(String authorName) {
        return bookRepository.findByMemberName(authorName);
    }

    public Book create(CreateBookServiceRequest request) {
        Member member = memberRepository.findById(request.getAuthorId());
        Book book = Book.of(request.getTitle(), request.getPublishedDate(), member);
        bookRepository.save(book);

        return book;
    }
}
