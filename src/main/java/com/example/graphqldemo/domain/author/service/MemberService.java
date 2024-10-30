package com.example.graphqldemo.domain.author.service;

import com.example.graphqldemo.domain.author.entity.Member;
import com.example.graphqldemo.domain.author.repository.MemberJpaRepository;
import com.example.graphqldemo.domain.author.repository.MemberRepository;
import com.example.graphqldemo.domain.author.service.request.CreateMemberServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberJpaRepository memberJpaRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberJpaRepository memberJpaRepository) {
        this.memberRepository = memberRepository;
        this.memberJpaRepository = memberJpaRepository;
    }

    public List<Member> getMembers(){
        return memberRepository.findAll();
    }

    public Member getMemberById(Long authorId){
        return memberRepository.findById(authorId);
    }

    public Member create(CreateMemberServiceRequest request) {
        Member member = new Member(request.getName(), request.getAge());

        memberJpaRepository.save(member);
        return member;
    }
}
