// 모든 비즈니스 로직들을 작성한다.
package com.example.bcsd.service;

import com.example.bcsd.domain.Member;
import com.example.bcsd.dto.MemberDTO;
import com.example.bcsd.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MemberService {
    // 우선 회원 리포지토리를 가져오자
    private final MemoryMemberRepository memberRepository;

    @Autowired // 생성자 호출할 때 MemberRepository(실제로는 구현부인 MemoryMemberRepository)를 넣어줌!
    public MemberService(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member create(Member member) {
        return memberRepository.insert(member);
    }

    public Member update(Long id, MemberDTO req) {
        Member member = memberRepository.findById(id);
        member.setName(req.getName());
        member.setEmail(req.getEmail());
        member.setPassword(req.getPassword());

        return memberRepository.update(member);
    }
    public Member getOne(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> searchByName(String name) {
        return memberRepository.findByName(name);
    }

    public boolean delete(Long id) {
        return memberRepository.deleteById(id);
    }
}
