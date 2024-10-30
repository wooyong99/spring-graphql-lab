package com.example.graphqldemo.domain.review.controller;

import com.example.graphqldemo.domain.review.controller.request.CreateReviewRequest;
import com.example.graphqldemo.domain.review.entity.Review;
import com.example.graphqldemo.domain.review.service.request.CreateReviewServiceRequest;
import com.example.graphqldemo.domain.review.service.ReviewService;
import com.example.graphqldemo.global.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @QueryMapping
    public List<Review> getReviews(){
        return reviewService.getReviews();
    }

    @QueryMapping
    public List<Review> getReviewsByBookId(@Argument Long id){
        return reviewService.getReviewsByBookId(id);
    }

    @QueryMapping
    public Review getReviewById(@Argument Long id){
        return reviewService.getReviewById(id);
    }

    @QueryMapping
    public List<Review> getReviewsByMemberName(@Argument String memberName){
        return reviewService.getReviewsByMemberName(memberName);
    }

    @MutationMapping
    public Review addReview(@Argument("input") CreateReviewRequest request){
        Long authorId = SecurityUtils.getAuthorId();
        CreateReviewServiceRequest serviceRequest = new CreateReviewServiceRequest(authorId, request.getBookId(), request.getContent(), request.getRating());

        return reviewService.create(serviceRequest);
    }

}
