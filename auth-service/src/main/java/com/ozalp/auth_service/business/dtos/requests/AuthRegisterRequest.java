package com.ozalp.auth_service.business.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRegisterRequest {
    private String email;
    private String password;
}
