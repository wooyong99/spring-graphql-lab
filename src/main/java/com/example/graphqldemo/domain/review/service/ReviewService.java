package com.example.graphqldemo.domain.review.service;

import com.example.graphqldemo.domain.author.entity.Member;
import com.example.graphqldemo.domain.author.repository.MemberRepository;
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

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, MemberRepository memberRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    public List<Review> getReviewsByBookId(Long id) {
        return reviewRepository.findByBookId(id);
    }

    public List<Review> getReviewsByMemberName(String memberName) {
        return reviewRepository.findByMemberName(memberName);
    }

    public List<Review> getReviewsByAuthorId(Long id) {
        return reviewRepository.findByMemberId(id);
    }

    public Review create(CreateReviewServiceRequest request) {
        Member member = memberRepository.findById(request.getAuthorId());
        Book book = bookRepository.findById(request.getBookId());

        Review review = Review.of(member, book, request.getContent(), request.getRating());
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
