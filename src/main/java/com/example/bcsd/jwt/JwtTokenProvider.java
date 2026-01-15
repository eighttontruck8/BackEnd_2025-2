package com.example.bcsd.jwt;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final Key key = new SecretKeySpec("secret".getBytes(), "AES");

    public String generateToken(Long memberId, String email) {
        return Jwts.builder()
                .subject(email)                     // 토큰 주인
                .claim("memberId", memberId)  // 커스텀 정보
                .issuedAt(new Date())               // 발급 시간
                .signWith(key)                      // 서명
                .compact();
    }
}
