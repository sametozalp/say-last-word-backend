package com.ozalp.auth_service.repositories;

import com.ozalp.auth_service.entities.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<Auth, UUID> {
    Optional<Auth> findByEmail(String email);
}
