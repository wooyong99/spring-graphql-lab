package com.example.graphqldemo.domain.review.entity;

import com.example.graphqldemo.domain.author.entity.Author;
import com.example.graphqldemo.domain.book.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Float rating;

    private LocalDate createdDate;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Author author;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Book book;

    private Review(Author author, Book book, String content, Float rating) {
        this.content = content;
        this.rating = rating;
        this.createdDate = LocalDate.now();
        setAuthor(author);
        setBook(book);
    }

    public static Review of(Author author, Book book, String content, Float rating) {
        return new Review(author, book, content, rating);
    }

    private void setAuthor(Author author) {
        if(this.author != null) {
            this.author.getReviews().remove(this);
        }
        this.author = author;
        this.author.getReviews().add(this);
    }

    private void setBook(Book book) {
        if(this.book != null) {
            this.book.getReviews().remove(this);
        }
        this.book = book;
        this.book.getReviews().add(this);
    }


}
