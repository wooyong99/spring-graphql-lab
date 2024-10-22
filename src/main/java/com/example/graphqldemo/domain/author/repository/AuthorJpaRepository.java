package com.example.graphqldemo.domain.author.repository;

import com.example.graphqldemo.domain.author.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorJpaRepository extends JpaRepository<Author,Long> {
}
