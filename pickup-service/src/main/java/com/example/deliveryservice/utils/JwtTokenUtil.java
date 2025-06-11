package com.example.deliveryservice.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor

/*
✅ 3. Vậy JWT chứa gì?
Thông tin bên trong phần Payload có thể là:

Key	Ý nghĩa
sub	Username hoặc ID người dùng
roles	Danh sách quyền hoặc vai trò
iat	(issued at) – thời điểm phát hành
exp	(expiration) – thời điểm hết hạn
iss	(issuer) – hệ thống phát hành token
aud	(audience) – đối tượng sử dụng token
 */
public class JwtTokenUtil {

    @Value("${jwt.expiration}")
    private int expiration; //save to an environment variable

    @Value("${jwt.secretKey}")
    private String secretKey;

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Long extractUserId(String token) {
        return extractClaims(token).get("userId", Long.class);
    }

    public List<String> extractRoles(String token) {
        return extractClaims(token).get("roles", List.class);
    }
}
