package com.example.bcsd.controller;

import com.example.bcsd.domain.Member;
import com.example.bcsd.dto.MemberDTO;
import com.example.bcsd.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // 1. member 생성
    @PostMapping
    public Member create(@RequestBody MemberDTO req) {
        Member member = new Member();
        member.setName(req.getName());
        member.setEmail(req.getEmail());
        member.setPassword(req.getPassword());

        return memberService.create(member);
    }

    // 2. member 정보 수정
    @PutMapping("/{id}")
    public Member update(@PathVariable Long id,
                         @RequestBody MemberDTO req) {
        return memberService.update(req, id);
    }

    // 3. member 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = memberService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build();  // 404
        }
    }
    // 4. member 조회
    // 4-1. id로 조회
    @GetMapping("/{id}")
    public Member getOne(@PathVariable Long id) {
        return memberService.getOne(id);
    }
    // 4-2. name으로 조회
    @GetMapping("/search")
    public List<Member> searchByName(@RequestParam String name) {
        return memberService.searchByName(name);
    }
}
