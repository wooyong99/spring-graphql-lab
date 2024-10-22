package com.example.graphqldemo.domain.author.service.request;

import lombok.Data;

@Data
public class CreateAuthorServiceRequest {

    private String authorName;

    public CreateAuthorServiceRequest(String authorName) {
        this.authorName = authorName;
    }
}
