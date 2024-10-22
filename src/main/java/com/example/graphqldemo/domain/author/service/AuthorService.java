package com.example.graphqldemo.domain.author.service;

import com.example.graphqldemo.domain.author.entity.Author;
import com.example.graphqldemo.domain.author.repository.AuthorJpaRepository;
import com.example.graphqldemo.domain.author.repository.AuthorRepository;
import com.example.graphqldemo.domain.author.service.request.CreateAuthorServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorJpaRepository authorJpaRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, @Qualifier("authorJpaRepository") AuthorJpaRepository authorJpaRepository) {
        this.authorRepository = authorRepository;
        this.authorJpaRepository = authorJpaRepository;
    }

    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long authorId){
        return authorRepository.findById(authorId);
    }

    public Author create(CreateAuthorServiceRequest request) {
        Author author = new Author(request.getAuthorName());

        authorJpaRepository.save(author);
        return author;
    }
}
