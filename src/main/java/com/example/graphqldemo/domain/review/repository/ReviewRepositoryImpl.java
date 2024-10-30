package com.example.graphqldemo.domain.review.repository;

import com.example.graphqldemo.domain.review.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    private final ReviewJpaRepository reviewJpaRepository;

    @Autowired
    public ReviewRepositoryImpl(ReviewJpaRepository reviewJpaRepository) {
        this.reviewJpaRepository = reviewJpaRepository;
    }

    @Override
    public void save(Review review) {
        reviewJpaRepository.save(review);
    }

    @Override
    public List<Review> findAll() {
        return reviewJpaRepository.findAll();
    }

    @Override
    public List<Review> findByMemberId(Long id) {
        return reviewJpaRepository.findByMemberId(id);
    }

    @Override
    public List<Review> findByBookId(Long id) {
        return reviewJpaRepository.findByBookId(id);
    }

    @Override
    public Review findById(Long id) {
        return reviewJpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Review> findByMemberName(String memberName) {
        return reviewJpaRepository.findByMemberName(memberName);
    }
}
