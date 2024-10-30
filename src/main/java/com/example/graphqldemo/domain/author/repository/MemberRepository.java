package com.example.graphqldemo.domain.author.repository;

import com.example.graphqldemo.domain.author.entity.Member;

import java.util.List;

public interface MemberRepository {
    List<Member> findAll();

    Member findById(Long authorId);
}
