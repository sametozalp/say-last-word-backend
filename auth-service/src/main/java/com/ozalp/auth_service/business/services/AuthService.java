package com.ozalp.auth_service.business.services;

import com.ozalp.auth_service.business.dtos.requests.AuthLoginWithEmailRequest;
import com.ozalp.auth_service.business.dtos.requests.AuthRegisterRequest;
import com.ozalp.auth_service.business.dtos.responses.AuthResponse;

public interface AuthService {

    AuthResponse register(AuthRegisterRequest request);

    AuthResponse login(AuthLoginWithEmailRequest request);

    AuthResponse refreshToken(String refreshToken);
}
