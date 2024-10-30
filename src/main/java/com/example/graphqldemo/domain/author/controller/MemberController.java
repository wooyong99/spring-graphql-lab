package com.example.graphqldemo.domain.author.controller;

import com.example.graphqldemo.domain.author.controller.request.CreateMemberRequest;
import com.example.graphqldemo.domain.author.entity.Member;
import com.example.graphqldemo.domain.author.service.MemberService;
import com.example.graphqldemo.domain.author.service.request.CreateMemberServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) { this.memberService = memberService; }

    @QueryMapping
    public List<Member> getAllMembers(){
        return memberService.getMembers();
    }

    @QueryMapping
    public Member getMemberById(@Argument Long id){
        return memberService.getMemberById(id);
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
    public Member addMember(@Argument("input") CreateMemberRequest request){
        CreateMemberServiceRequest serviceRequest = new CreateMemberServiceRequest(request.getName(), request.getAge());
        return memberService.create(serviceRequest);
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
