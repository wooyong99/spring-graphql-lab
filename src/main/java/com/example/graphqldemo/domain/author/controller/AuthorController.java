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

/*    스키마 파일 구조
    type Mutation {
        addAuthor(input : CreateAuthorRequest): Author
    }
    요청 쿼리
    mutation {
         addAuthor(input : {authorName : "Hello") {
             id
             name
       }
    }*/

    @MutationMapping
    public Author addAuthor(@Argument("input") CreateAuthorRequest request){
        CreateAuthorServiceRequest serviceRequest = new CreateAuthorServiceRequest(request.getAuthorName());
        return authorService.create(serviceRequest);
    }
/*
    Argument 와 Arguments 차이점
    Argument는 단일 인자를 처리할 때 사용된다. 스칼라 타입이나 간단한 객체를 인자로 받을 때 사용한다.
    Arguments는 여러 인자를 포함하는 복잡한 요청을 한번에 객체로 매핑하여 처리할 때 사용한다.
*/

/*    스키마 구조
    type Mutation {
        addAuthor(authorName : String ): Author
    }
    요청 쿼리
    mutation {
        addAuthor(authorName : "Hello"){
            id
            name
        }
    }

    @MutationMapping
    public Author addAuthor(@Arguments CreateAuthorRequest request){
        System.out.println(request.getAuthorName());
        CreateAuthorServiceRequest serviceRequest = new CreateAuthorServiceRequest(request.getAuthorName());
        return authorService.create(serviceRequest);
    }*/
}
