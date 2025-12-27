package com.ozalp.auth_service.repositories;

import com.ozalp.auth_service.entities.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthRepository extends JpaRepository<Auth, UUID> {
}
