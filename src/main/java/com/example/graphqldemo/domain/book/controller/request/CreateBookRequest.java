package com.example.graphqldemo.domain.book.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    public String title;
    public Date publishedDate;
}
