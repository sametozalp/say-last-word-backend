package com.ozalp.auth_service.business.impls;

import com.ozalp.auth_service.business.services.RefreshTokenService;
import com.ozalp.auth_service.entities.RefreshToken;
import com.ozalp.auth_service.repositories.RefreshTokenRepository;
import com.ozalp.auth_service.util.Messages;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenImpl implements RefreshTokenService {

    private final RefreshTokenRepository repository;

    @Override
    public RefreshToken findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.RefreshToken.NOT_FOUND));
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return repository.save(refreshToken);
    }

    @Override
    public void deleteUserRefreshTokens(UUID authId) {
        List<RefreshToken> refreshTokens = repository.findByAuthIdAndDeletedAtIsNull(authId);
        for (RefreshToken refreshToken : refreshTokens) {
            refreshToken.markAsDeleted();
            repository.save(refreshToken);
        }
    }

    @Override
    public RefreshToken findByRefreshToken(String refreshToken) {
        return repository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new EntityNotFoundException(Messages.RefreshToken.NOT_FOUND));
    }
}
