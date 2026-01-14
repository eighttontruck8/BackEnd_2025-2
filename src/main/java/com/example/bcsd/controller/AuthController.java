package com.example.bcsd.controller;

import com.example.bcsd.dto.MemberDTO;
import com.example.bcsd.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberDTO req) {
        memberService.login(req);
        return ResponseEntity.ok("login success");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberDTO req) {
        memberService.signup(req);
        return ResponseEntity.ok("signup success");
    }
}
