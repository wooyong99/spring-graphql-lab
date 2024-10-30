package com.example.graphqldemo.domain.author.repository;

import com.example.graphqldemo.domain.author.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member,Long> {
}
