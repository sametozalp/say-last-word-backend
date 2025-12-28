package com.ozalp.auth_service.business.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class AuthResponse {
    private UUID id;
    private String email;
    private String username;
    private String accessToken;
    private String refreshToken;
}
