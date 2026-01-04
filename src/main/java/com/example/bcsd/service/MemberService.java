// 모든 비즈니스 로직들을 작성한다.
package com.example.bcsd.service;
import com.example.bcsd.domain.Member;
import com.example.bcsd.dto.MemberDTO;
import com.example.bcsd.exception.DuplicateEmailException;
import com.example.bcsd.exception.MissingFieldException;
import com.example.bcsd.exception.RemainArticlesException;
import com.example.bcsd.repository.ArticleRepository;
import com.example.bcsd.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MemberService {
    private final MemoryMemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    @Autowired // 생성자 호출할 때 MemberRepository(실제로는 구현부인 MemoryMemberRepository)를 넣어줌!
    public MemberService(MemoryMemberRepository memberRepository, ArticleRepository articleRepository) {
        this.memberRepository = memberRepository;
        this.articleRepository = articleRepository;
    }

    public Member create(MemberDTO req) {
        validateForCreate(req);

        Member member = new Member();
        member.setName(req.getName());
        member.setEmail(req.getEmail());
        member.setPassword(req.getPassword());

        return memberRepository.insert(member);
    }

    public Member update(MemberDTO req, Long id) {
        Member member = memberRepository.findById(id);

        if (memberRepository.existsByEmail(req.getEmail())
                && !member.getEmail().equals(req.getEmail())) {
            throw new DuplicateEmailException("이미 사용 중인 이메일입니다: " + req.getEmail());
        }

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
        if (articleRepository.existsByBoardId(id)){
            throw new RemainArticlesException("게시판에 작성한 게시글이 남아있어 삭제가 불가능합니다. member_id =" + id);
        }
        return memberRepository.deleteById(id);
    }

    private void validateForCreate(MemberDTO req) {

        if (req.getName() == null || req.getName().isBlank()) {
            throw new MissingFieldException("name은 null일 수 없습니다.");
        }

        if (req.getEmail() == null || req.getEmail().isBlank()) {
            throw new MissingFieldException("email은 null일 수 없습니다.");
        }

        if (req.getPassword() == null || req.getPassword().isBlank()) {
            throw new MissingFieldException("password은 null일 수 없습니다.");
        }
    }
    private void validateForDelete(MemberDTO req) {

    }
}
