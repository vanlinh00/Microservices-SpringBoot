package com.example.deliveryservice.utils;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    public String generateToken(UserDetails userDetails) {

        // Bước 1: Tạo Claims = Key, Value
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        // Bước 2: Jwt.builder() rồi gắn cái claim lại
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

//
//    // chuyển đổi từ secret key sang đối tượng key
//    private Key getSignInKey() {
//        byte[] bytes = Decoders.BASE64.decode(secretKey);
//        //Keys.hmacShaKeyFor(Decoders.BASE64.decode("TaqlmGv1iEDMRiFp/pHuID1+T84IABfuA0xXh4GhiUI="));
//        return Keys.hmacShaKeyFor(bytes);
//    }

//    private String generateSecretKey() {
//        SecureRandom random = new SecureRandom();
//        byte[] keyBytes = new byte[32]; // 256-bit key
//        random.nextBytes(keyBytes);
//        String secretKey = Encoders.BASE64.encode(keyBytes);
//        return secretKey;
//    }

//    // lấy ra Claim
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(getSignInKey())  //  chuyền thằng Signingkey vào trong token và sẽ lấy được claim vì claim lằm trong body
//               .parseClaimsJws(token).getBody();
//    }

//    // extract tất cả claim và lấy ra cái claim mong muốn
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = this.extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }

//    //check expiration  // Khiểm tra token đã hết hạn chưa
//    public boolean isTokenExpired(String token) {
//        Date expirationDate = this.extractClaim(token, Claims::getExpiration);
//        return expirationDate.before(new Date());
//    }
//
//    public String extractPhoneNumber(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }

//    public boolean validateToken(String token, UserDetails userDetails) {
//        String phoneNumber = extractPhoneNumber(token);
//        return (phoneNumber.equals(userDetails.getUsername())) && !isTokenExpired(token);
//    }
//
//    public String extractUsername(String token) {
//        return Jwts.parser().setSigningKey(secretKey)  // Sử dụng secretKey khi xác thực
//             .parseClaimsJws(token).getBody().getSubject();
//    }

//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()));
//    }
}
