package me.ham.shop.controller;

import me.ham.shop.entity.Member;
import me.ham.shop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping(value = "/member/{id}")
    public Member getMember(@PathVariable long id){
        return memberRepository.getMember(id);
    }

    @GetMapping(value = "/members")
    public List<Member> getMember(){
        return memberRepository.getMembers();
    }

    @PostMapping(value = "/member")
    public void putMember(@RequestBody Member member){
        memberRepository.insertMember(member);
    }

}
