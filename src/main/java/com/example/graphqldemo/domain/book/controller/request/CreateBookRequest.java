package com.example.graphqldemo.domain.book.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    public String title;

    public LocalDate publishedDate;
}
