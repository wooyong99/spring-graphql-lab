package com.example.graphqldemo.domain.author.controller;

import com.example.graphqldemo.domain.author.controller.request.CreateAuthorRequest;
import com.example.graphqldemo.domain.author.entity.Author;
import com.example.graphqldemo.domain.author.service.AuthorService;
import com.example.graphqldemo.domain.author.service.request.CreateAuthorServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) { this.authorService = authorService; }

    @QueryMapping
    public List<Author> getAllAuthors(){
        return authorService.getAuthors();
    }

    @QueryMapping
    public Author getAuthorById(@Argument Long id){
        return authorService.getAuthorById(id);
    }

    @MutationMapping
    public Author addAuthor(@Argument CreateAuthorRequest request){
        CreateAuthorServiceRequest serviceRequest = new CreateAuthorServiceRequest(request.getAuthorName());
        return authorService.create(serviceRequest);
    }
}
