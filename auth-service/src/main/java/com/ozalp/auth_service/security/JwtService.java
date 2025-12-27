package com.ozalp.auth_service.security;

import com.ozalp.auth_service.entities.Auth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expirationMinute}")
    private long expirationMinute;

    public String generateAccessToken(Auth auth) {
        return Jwts.builder()
                .subject(auth.getEmail())
                .claim("username", auth.getUsername())
                //.claim("roles", user.getAllRoles())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * expirationMinute))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public List<String> extractRoles(String token) {
        return getClaims(token).get("roles", List.class);
    }

    public Date extractExpiration(String token) {
        return getClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, String email) {
        return email.equals(extractEmail(token)) && !isTokenExpired(token);
    }
}
