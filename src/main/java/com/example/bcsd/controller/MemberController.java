package com.example.bcsd.controller;

import com.example.bcsd.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    // private final MemberService memberService = new MemberService();
    // 이렇게 굳이 여러 개 생성할 필요 없음.
    // 스프링 컨테이너에 bean으로 등록하면 한 번만 생성해도 된다.

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
