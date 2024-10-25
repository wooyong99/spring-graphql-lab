package com.example.graphqldemo.domain.book.service.request;

import lombok.Data;

import java.util.Date;

@Data
public class CreateBookServiceRequest {
    public Long authorId;
    public String title;
    public Date publishedDate;

    public CreateBookServiceRequest(Long authorId, String title, Date publishedDate) {
        this.authorId = authorId;
        this.title = title;
        this.publishedDate = publishedDate;
    }
}
