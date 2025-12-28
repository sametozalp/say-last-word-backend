package com.ozalp.auth_service.business.impls;

import com.ozalp.auth_service.business.services.JwtService;
import com.ozalp.auth_service.business.services.RefreshTokenService;
import com.ozalp.auth_service.entities.Auth;
import com.ozalp.auth_service.entities.RefreshToken;
import com.ozalp.auth_service.exceptions.errors.InvalidTokenException;
import com.ozalp.auth_service.util.Messages;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtServiceImpl implements JwtService {

    private final RefreshTokenService refreshTokenService;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expirationMinute}")
    private long expirationMinute;

    public JwtServiceImpl(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    @Cacheable(value = "accessTokens", key = "#auth.getId()")
    public String generateAccessToken(Auth auth) {
        return Jwts.builder()
                .subject(auth.getId().toString())
                .claim("email", auth.getEmail())
                .claim("username", auth.getUsername())
                .claim("role", auth.getRole())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * expirationMinute))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSignKey())
                .compact();
    }

    public RefreshToken generateRefreshToken(Auth auth) {
        refreshTokenService.deleteUserRefreshTokens(auth.getId());
        String token = UUID.randomUUID().toString() + System.currentTimeMillis();
        LocalDateTime expiration = LocalDateTime.now().plusDays(7);
        RefreshToken refreshToken = new RefreshToken(auth, token, expiration);
        return refreshTokenService.save(refreshToken);
    }

    public Pair<String, RefreshToken> refreshAccessToken(String refreshToken) {
        RefreshToken refreshTokenEntity = refreshTokenService.findByRefreshToken(refreshToken);
        if (refreshTokenEntity.isExpired()) {
            throw new InvalidTokenException(Messages.RefreshToken.INVALID_TOKEN);
        }
        Auth auth = refreshTokenEntity.getAuth();
        return new Pair<>(generateAccessToken(auth), generateRefreshToken(auth));
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

    public String extractAuthId(String token) {
        return getClaims(token).getSubject();
    }


    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    public Date extractExpiration(String token) {
        return getClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, String email) {
        return email.equals(extractAuthId(token)) && !isTokenExpired(token);
    }
}
