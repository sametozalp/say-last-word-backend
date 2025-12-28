package com.ozalp.auth_service.business.impls;

import com.ozalp.auth_service.business.dtos.requests.AuthLoginWithEmailRequest;
import com.ozalp.auth_service.business.dtos.requests.AuthRegisterRequest;
import com.ozalp.auth_service.business.dtos.responses.AuthResponse;
import com.ozalp.auth_service.business.enums.RoleEnum;
import com.ozalp.auth_service.business.mappers.AuthMapper;
import com.ozalp.auth_service.business.services.AuthService;
import com.ozalp.auth_service.business.services.RefreshTokenService;
import com.ozalp.auth_service.entities.Auth;
import com.ozalp.auth_service.entities.RefreshToken;
import com.ozalp.auth_service.exceptions.errors.InvalidAuthCredentials;
import com.ozalp.auth_service.repositories.AuthRepository;
import com.ozalp.auth_service.util.Messages;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository repository;
    private final AuthMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtServiceImpl;
    private final RefreshTokenService refreshTokenService;
    private CacheManager cacheManager;

    @Transactional
    @Override
    public AuthResponse register(AuthRegisterRequest request) {
        Auth reqAuth = mapper.toEntity(request);
        reqAuth.setEmail(reqAuth.getEmail().trim());
        reqAuth.setPassword(passwordEncoder.encode(reqAuth.getPassword()));
        reqAuth.setIsActive(true);
        reqAuth.setUsername("user" + UUID.randomUUID().toString().toLowerCase());
        reqAuth.setRole(RoleEnum.USER);
        Auth saved = repository.save(reqAuth);

        return AuthResponse.builder()
                .id(saved.getId())
                .email(saved.getEmail())
                .username(saved.getUsername())
                .accessToken(jwtServiceImpl.generateAccessToken(reqAuth))
                .refreshToken(jwtServiceImpl.generateRefreshToken(reqAuth).getRefreshToken())
                .build();
    }

    @Transactional
    @Override
    public AuthResponse login(AuthLoginWithEmailRequest request) {
        Auth reqAuth = mapper.toEntity(request);
        reqAuth.setEmail(reqAuth.getEmail().trim());

        Auth dbAuth = findByEmail(reqAuth.getEmail());
        if (!passwordEncoder.matches(reqAuth.getPassword(), dbAuth.getPassword())) {
            throw new InvalidAuthCredentials(Messages.AuthMessages.INVALID_AUTH_CREDENTIALS);
        }

        return AuthResponse.builder()
                .id(dbAuth.getId())
                .email(dbAuth.getEmail())
                .username(dbAuth.getUsername())
                .accessToken(jwtServiceImpl.generateAccessToken(dbAuth))
                .refreshToken(jwtServiceImpl.generateRefreshToken(dbAuth).getRefreshToken())
                .build();
    }

    @Override
    public AuthResponse refreshToken(String refreshToken) {
        RefreshToken dbRefreshToken = refreshTokenService.findByRefreshToken(refreshToken);
        dbRefreshToken.markAsDeleted();
        refreshTokenService.save(dbRefreshToken);

        Auth auth = dbRefreshToken.getAuth();
        return AuthResponse.builder()
                .id(auth.getId())
                .email(auth.getEmail())
                .username(auth.getUsername())
                .accessToken(jwtServiceImpl.generateAccessToken(auth))
                .refreshToken(jwtServiceImpl.generateRefreshToken(auth).getRefreshToken())
                .build();
    }

    @Override
    public boolean existsAdmin() {
        return repository.existsByUsername("admin");
    }

    @Override
    public Auth save(Auth auth) {
        return repository.save(auth);
    }

    @Override
    public boolean validate(String accessToken) {
        Cache cache = cacheManager.getCache("accessTokens");
        if (cache == null) {
            return false;
        }

        Cache.ValueWrapper valueWrapper = cache.get(jwtServiceImpl.extractAuthId(accessToken));
        return valueWrapper != null;
    }

    private Auth findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(Messages.AuthMessages.NOT_FOUND));
    }
}
