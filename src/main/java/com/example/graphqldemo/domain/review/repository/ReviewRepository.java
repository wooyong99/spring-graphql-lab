package com.example.graphqldemo.domain.review.repository;

import com.example.graphqldemo.domain.review.entity.Review;

import java.util.List;

public interface ReviewRepository {
    void save(Review review);

    List<Review> findAll();

    List<Review> findByAuthorId(Long id);

    List<Review> findByBookId(Long id);

    Review findById(Long id);

    List<Review> findByAuthorName(String authorName);
}
