package com.ozalp.auth_service.business.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String email;
    private String username;
}
