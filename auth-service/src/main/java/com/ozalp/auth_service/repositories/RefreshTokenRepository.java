package com.ozalp.auth_service.repositories;

import com.ozalp.auth_service.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    List<RefreshToken> findByAuthId(UUID authId);

    Optional<RefreshToken> findByRefreshTokenAndDeletedAtIsNull(String refreshToken);
}
