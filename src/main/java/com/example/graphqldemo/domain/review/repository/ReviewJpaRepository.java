package com.example.graphqldemo.domain.review.repository;

import com.example.graphqldemo.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {
    List<Review> findByBookId(Long id);

    List<Review> findByAuthorId(Long id);

    @Query("SELECT r FROM Review r WHERE r.author.name = :authorName")
    List<Review> findByAuthorName(String authorName);
}
