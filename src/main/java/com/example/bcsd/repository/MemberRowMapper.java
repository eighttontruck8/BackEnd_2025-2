package com.example.bcsd.repository;

import com.example.bcsd.domain.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {
    Member member = new Member();
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {

        // setter
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPassword(rs.getString("password"));

        return member;
}
}
