package com.example.graphqldemo.domain.book.service.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateBookServiceRequest {
    public Long authorId;
    public String title;
    public LocalDate publishedDate;

    public CreateBookServiceRequest(Long authorId, String title, LocalDate publishedDate) {
        this.authorId = authorId;
        this.title = title;
        this.publishedDate = publishedDate;
    }
}
