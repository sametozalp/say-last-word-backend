package com.ozalp.auth_service.controllers;

import com.ozalp.auth_service.business.dtos.requests.AuthLoginWithEmailRequest;
import com.ozalp.auth_service.business.dtos.requests.AuthRegisterRequest;
import com.ozalp.auth_service.business.services.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ozalp.auth_service.util.Constants.*;

@RestController
@RequestMapping(V1 + AUTH)
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(REGISTER)
    ResponseEntity<?> register(@Valid @RequestBody AuthRegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping(LOGIN)
    ResponseEntity<?> login(@Valid @RequestBody AuthLoginWithEmailRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(REFRESH_TOKEN + "/{refreshToken}")
    ResponseEntity<?> refreshToken(@PathVariable String refreshToken) {
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }
}
