package com.ozalp.auth_service.business.services;

import com.ozalp.auth_service.business.dtos.requests.AuthLoginWithEmailRequest;
import com.ozalp.auth_service.business.dtos.requests.AuthRegisterRequest;
import com.ozalp.auth_service.business.dtos.responses.AuthResponse;
import com.ozalp.auth_service.business.enums.RoleEnum;
import com.ozalp.auth_service.business.mappers.AuthMapper;
import com.ozalp.auth_service.entities.Auth;
import com.ozalp.auth_service.repositories.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository repository;
    private final AuthMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(AuthRegisterRequest request) {
        Auth reqAuth = mapper.toEntity(request);
        reqAuth.setEmail(reqAuth.getEmail().trim());
        reqAuth.setPassword(passwordEncoder.encode(reqAuth.getPassword()));
        reqAuth.setIsActive(true);
        reqAuth.setUsername("user" + UUID.randomUUID().toString().toLowerCase());
        reqAuth.setRole(RoleEnum.USER);
        return mapper.toResponse(repository.save(reqAuth));
    }

    @Override
    public AuthResponse login(AuthLoginWithEmailRequest request) {
        Auth reqAuth = mapper.toEntity(request);
        reqAuth.setEmail(reqAuth.getEmail().trim());

        Optional<Auth> auth = repository.findByEmailAndPassword(reqAuth.getEmail(), reqAuth.getPassword());
        return null;
    }
}
