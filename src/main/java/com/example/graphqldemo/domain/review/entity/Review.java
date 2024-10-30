package com.example.graphqldemo.domain.review.entity;

import com.example.graphqldemo.domain.author.entity.Member;
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
    private Member member;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Book book;

    private Review(Member member, Book book, String content, Float rating) {
        this.content = content;
        this.rating = rating;
        this.createdDate = LocalDate.now();
        setMember(member);
        setBook(book);
    }

    public static Review of(Member member, Book book, String content, Float rating) {
        return new Review(member, book, content, rating);
    }

    private void setMember(Member member) {
        if(this.member != null) {
            this.member.getReviews().remove(this);
        }
        this.member = member;
        this.member.getReviews().add(this);
    }

    private void setBook(Book book) {
        if(this.book != null) {
            this.book.getReviews().remove(this);
        }
        this.book = book;
        this.book.getReviews().add(this);
    }


}
