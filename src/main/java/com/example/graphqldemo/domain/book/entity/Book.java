package com.example.graphqldemo.domain.book.entity;

import com.example.graphqldemo.domain.author.entity.Member;
import com.example.graphqldemo.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    private Member member;

    @OneToMany(mappedBy = "book", cascade = {CascadeType.PERSIST,
            CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Review> reviews = new HashSet<>();

    protected Book(String title, LocalDate publishedDate, Member member){
        this.title = title;
        this.publishedDate = publishedDate;
        setMember(member);
    }

    private void setMember(Member member) {
        if(this.member != null) {
            this.member.getBooks().remove(this);
        }
        this.member = member;
        this.member.getBooks().add(this);
    }

    public static Book of(String title, LocalDate publishedDate, Member member){
        return new Book(title, publishedDate, member);
    }
}
