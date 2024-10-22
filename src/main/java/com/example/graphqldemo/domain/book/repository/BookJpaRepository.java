package com.example.graphqldemo.domain.book.repository;

import com.example.graphqldemo.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookJpaRepository extends JpaRepository<Book,Long>{

    // JPQL을 사용하여 authorName으로 book 리스트를 찾는 메서드
    @Query("SELECT b FROM Book b WHERE b.author.name = :authorName")
    List<Book> findByAuthorName(@Param("authorName") String authorName);
}
