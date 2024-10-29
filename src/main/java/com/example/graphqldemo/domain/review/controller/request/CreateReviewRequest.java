package com.example.graphqldemo.domain.review.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewRequest {

    Long bookId;

    String content;

    Float rating;

}
