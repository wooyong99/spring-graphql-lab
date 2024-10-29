package com.example.graphqldemo.domain.review.service;

import com.example.graphqldemo.domain.author.entity.Author;
import com.example.graphqldemo.domain.author.repository.AuthorRepository;
import com.example.graphqldemo.domain.book.entity.Book;
import com.example.graphqldemo.domain.book.repository.BookRepository;
import com.example.graphqldemo.domain.review.entity.Review;
import com.example.graphqldemo.domain.review.repository.ReviewRepository;
import com.example.graphqldemo.domain.review.service.request.CreateReviewServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Review> getReviewsByBookId(Long id) {
        return reviewRepository.findByBookId(id);
    }

    public List<Review> getReviewsByAuthorName(String authorName) {
        return reviewRepository.findByAuthorName(authorName);
    }

    public List<Review> getReviewsByAuthorId(Long id) {
        return reviewRepository.findByAuthorId(id);
    }

    public Review create(CreateReviewServiceRequest request) {
        Author author = authorRepository.findById(request.getAuthorId());
        Book book = bookRepository.findById(request.getBookId());

        Review review = Review.of(author, book, request.getContent(), request.getRating());
        reviewRepository.save(review);
        return review;
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }
}
