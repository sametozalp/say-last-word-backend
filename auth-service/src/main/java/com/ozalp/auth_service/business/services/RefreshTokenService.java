package com.ozalp.auth_service.business.services;

import com.ozalp.auth_service.entities.RefreshToken;

import java.util.UUID;

public interface RefreshTokenService {

    void deleteUserRefreshTokens(UUID authId);

    RefreshToken findByRefreshToken(String refreshToken);

    RefreshToken save(RefreshToken refreshToken);

    RefreshToken findById(UUID id);

}
