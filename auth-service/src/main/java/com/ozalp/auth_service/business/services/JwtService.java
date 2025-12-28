package com.ozalp.auth_service.business.services;

import com.ozalp.auth_service.entities.Auth;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.util.Date;

public interface JwtService {
    String generateAccessToken(Auth auth);

    String extractEmail(String token);

    String extractRole(String token);

    Date extractExpiration(String token);

    boolean isTokenExpired(String token);

    Boolean validateToken(String token, String email);
}
