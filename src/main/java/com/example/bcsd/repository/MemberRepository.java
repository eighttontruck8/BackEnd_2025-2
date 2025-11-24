package com.example.bcsd.repository;
import com.example.bcsd.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //ID로 회원 정보 가져오기
    Optional<Member> findByName(String name); // 이름으로 회원 정보 가져오기
    List<Member> findAll(); // 모든 회원 정보 가져오기
}
