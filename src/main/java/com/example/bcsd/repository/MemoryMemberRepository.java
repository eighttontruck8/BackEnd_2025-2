package com.example.bcsd.repository;
import com.example.bcsd.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbctemplate;
    private final MemberRowMapper rowMapper = new MemberRowMapper();

    public MemoryMemberRepository(JdbcTemplate jdbctemplate) {
        this.jdbctemplate = jdbctemplate;
    }

    @Override
    public Member insert(Member member) {
        String sql = "INSERT INTO member (name, email, password)"
                + "VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbctemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setString(3, member.getPassword());
            return ps;
        }, keyHolder);

        Long newId = keyHolder.getKey().longValue();
        return findById(newId);
    }

    @Override
    public Member update(Member member) {
        String sql = "UPDATE member SET name = ?, email = ? , password=? WHERE id = ?";
        jdbctemplate.update(sql, member.getName(), member.getEmail(), member.getPassword(), member.getId());
        return findById(member.getId());
    }

    @Override
    public Member findById(Long id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        return jdbctemplate.queryForObject(sql, new MemberRowMapper(), id);
    }

    @Override
    public List<Member> findByName(String name) { // 동명이인 발생 가능
        String sql = "SELECT * FROM member where name = ?";
        return jdbctemplate.query(sql, rowMapper, name);
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM member WHERE email = ?";
        Integer count = jdbctemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM member WHERE id = ?";
        Integer count = jdbctemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public List<Member> findAll() {
        String sql = "SELECT * FROM member";
        return jdbctemplate.query(sql, rowMapper);
    }

    @Override
    public boolean deleteById(Long id){
        String sql = "DELETE FROM article WHERE id = ?";
        int rows = jdbctemplate.update(sql, id);

        if (rows == 0) {
            throw new IllegalArgumentException(id + "번 사용자가 존재하지 않습니다.");
        }
        return false;
    }

}
