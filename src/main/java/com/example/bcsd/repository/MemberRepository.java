package com.example.bcsd.repository;
import com.example.bcsd.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    List<Member> findByName(String name);
}
