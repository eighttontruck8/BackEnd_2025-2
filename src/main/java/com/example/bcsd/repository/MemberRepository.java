package com.example.bcsd.repository;
import com.example.bcsd.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member insert(Member member);
    Member update(Member member);
    Member findById(Long id); //ID로 회원 정보 가져오기
    List<Member> findByName(String name); // 이름으로 회원 정보 가져오기
    List<Member> findAll(); // 이름으로 회원 정보 가져오기
    boolean existsByEmail(String email);
    boolean existsById(Long id);
    boolean deleteById(Long id);
}
