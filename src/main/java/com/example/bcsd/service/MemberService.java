// 모든 비즈니스 로직들을 작성한다.
package com.example.bcsd.service;

import com.example.bcsd.domain.Member;
import com.example.bcsd.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    // 우선 회원 리포지토리를 가져오자.
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member){
        // 신규 회원 정보 저장
        // 만약 동명이인이 있다면, 불가능하다.
//        // [1] 정직한 버전
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        // 만약 값이 이미 존재한다면: 예외처리
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//        });

        // [2] 축약 버전
//            memberRepository.findByName(member.getName());
//              .ifPresent(m -> {
//              throw new IllegalStateException("이미 존재하는 회원입니다");
//               });

        // [3] 메서드로 추출한 버전 (길어져서)
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다");
            });
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // id로 고객 찾기
    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }
}
