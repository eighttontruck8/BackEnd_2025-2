// 모든 비즈니스 로직들을 작성한다.
package com.example.bcsd.service;
import com.example.bcsd.domain.Member;
import com.example.bcsd.dto.MemberDTO;
import com.example.bcsd.exception.DuplicateEmailException;
import com.example.bcsd.exception.MissingFieldException;
import com.example.bcsd.exception.RemainArticlesException;
import com.example.bcsd.repository.ArticleRepository;
import com.example.bcsd.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired // 생성자 호출할 때 MemberRepository(실제로는 구현부인 MemoryMemberRepository)를 넣어줌!
    public MemberService(MemberRepository memberRepository, ArticleRepository articleRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.articleRepository = articleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member create(MemberDTO req) {
        validateForCreate(req);

        Member member = new Member();
        member.setName(req.getName());
        member.setEmail(req.getEmail());
        member.setPassword(passwordEncoder.encode(req.getPassword()));
        return memberRepository.save(member);
    }

    public Member update(MemberDTO req, Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "번 유저는 존재하지 않습니다."));

        if (memberRepository.existsByEmail(req.getEmail())
                && !member.getEmail().equals(req.getEmail())) {
            throw new DuplicateEmailException("이미 사용 중인 이메일입니다: " + req.getEmail());
        }

        member.setName(req.getName());
        member.setEmail(req.getEmail());
        member.setPassword(passwordEncoder.encode(req.getPassword())); // 암호화로 변경

        return memberRepository.save(member);
    }

    public Member getOne(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 id입니다."));
    }

    public List<Member> searchByName(String name) {
        return memberRepository.findByName(name);
    }

    public void delete(Long id) {
        if (articleRepository.existsByAuthorId(id)){
            throw new RemainArticlesException("회원이 작성한 게시글이 남아있어 삭제가 불가능합니다. member_id =" + id);
        }
        memberRepository.deleteById(id);
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
    public Member login(MemberDTO req) {
        String email = req.getEmail();
        String password = req.getPassword();
        if (email == null || email.isBlank()) { throw new MissingFieldException("email은 null일수 없습니다.");}
        if (password == null || password.isBlank()) { throw new MissingFieldException("password는 null일수 없습니다.");}

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호를 다시 확인해주세요."));
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호를 다시 확인해주세요.");
        }
        return member;
    }
}
