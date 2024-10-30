package com.example.graphqldemo.domain.author.service.request;

import lombok.Data;

@Data
public class CreateMemberServiceRequest {

    private String name;

    private Long age;

    public CreateMemberServiceRequest(String name, Long age) {
        this.name = name;
        this.age = age;
    }
}
