package com.ozalp.auth_service.business.services;

import com.ozalp.auth_service.business.dtos.requests.AuthLoginWithEmailRequest;
import com.ozalp.auth_service.business.dtos.requests.AuthRegisterRequest;
import com.ozalp.auth_service.business.dtos.responses.AuthResponse;
import com.ozalp.auth_service.entities.Auth;

public interface AuthService {

    AuthResponse register(AuthRegisterRequest request);

    AuthResponse login(AuthLoginWithEmailRequest request);

    AuthResponse refreshToken(String refreshToken);

    boolean existsAdmin();

    Auth save(Auth auth);
}
