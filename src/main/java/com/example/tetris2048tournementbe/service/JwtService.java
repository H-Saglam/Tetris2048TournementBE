package com.example.tetris2048tournementbe.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Getter
@Setter
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    private String token;

    public String generateToken(String username) {
        Map<String,Object> claims = new HashMap<>();
        //put any other claims here
        this.token = createToken(claims,username);
        return token;
    }

    public boolean validateToken(String token, UserDetails userDetails){
        String username = extractUser(token);
        Date expiration = extractExpiration(token);
        return (username.equals(userDetails.getUsername()) && !expiration.before(new Date()));
    }

    private Date extractExpiration(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }
    public String extractUser(String token) {
        if (token == null || token.trim().isEmpty() || token.chars().filter(ch -> ch == '.').count() != 2) {
            throw new org.springframework.security.authentication.AuthenticationCredentialsNotFoundException("JWT token eksik veya geçersiz.");
        }
        try {
            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            throw new org.springframework.security.authentication.CredentialsExpiredException("JWT token süresi dolmuş.");
        } catch (Exception e) {
            throw new org.springframework.security.authentication.BadCredentialsException("JWT token doğrulanamadı.");
        }
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
