package com.example.graphqldemo.domain.author.entity;

import com.example.graphqldemo.domain.book.entity.Book;
import com.example.graphqldemo.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST,
            CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<>();

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST,
            CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Review> reviews = new HashSet<>();

    public Author(String name){
        this.name = name;
    }

    public void addBook(Book book){
        this.books.add(book);
    }

    public void addReview(Review review){
        this.reviews.add(review);
    }

}
