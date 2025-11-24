package com.example.bcsd.repository;

import com.example.bcsd.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    // 실제 저장되는 곳
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // key값 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id값 설정
        store.put(member.getId(), member); //id값+member 합치기
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // return store.get(id); // 없으면 null이 반환되기 때문에...
        return Optional.ofNullable(store.get(id)); // Optional.ofNullable로 감싸면 클라이언트에서 대처 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // 두 값이 같은 경우에만 필터링 됨
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // values가 member들임!
    }

    public void clearStore(){
        store.clear(); // 초기화
    }
}
