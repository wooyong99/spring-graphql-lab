package com.example.graphqldemo.domain.book.entity;

import com.example.graphqldemo.domain.author.entity.Author;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate publishedDate;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Author author;

    protected Book(String title, LocalDate publishedDate, Author author){
        this.title = title;
        this.publishedDate = publishedDate;
        setAuthor(author);
    }

    private void setAuthor(Author author) {
        if(this.author != null) {
            this.author.getBooks().remove(this);
        }
        this.author = author;
        this.author.getBooks().add(this);
    }

    public static Book of(String title, LocalDate publishedDate, Author author){
        return new Book(title, publishedDate, author);
    }
}
