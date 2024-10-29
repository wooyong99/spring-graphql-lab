package com.example.graphqldemo.domain.review.service.request;

import lombok.Data;

@Data
public class CreateReviewServiceRequest {

    public Long authorId;
    public Long bookId;
    public String content;
    public Float rating;

    public CreateReviewServiceRequest(Long authorId, Long bookId, String content, Float rating) {
        this.authorId = authorId;
        this.bookId = bookId;
        this.content = content;
        this.rating = rating;
    }
}
