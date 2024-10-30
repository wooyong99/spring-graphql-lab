package com.example.graphqldemo.domain.author.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberRequest {

    public String name;
    public Long age;
}
