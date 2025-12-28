package com.ozalp.auth_service.controllers;

import com.ozalp.auth_service.business.dtos.requests.AuthLoginWithEmailRequest;
import com.ozalp.auth_service.business.dtos.requests.AuthRegisterRequest;
import com.ozalp.auth_service.business.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ozalp.auth_service.util.Constants.*;

@RestController
@RequestMapping(V1 + AUTH)
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(REGISTER)
    ResponseEntity<?> register(@RequestBody AuthRegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping(LOGIN)
    ResponseEntity<?> login(@RequestBody AuthLoginWithEmailRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
