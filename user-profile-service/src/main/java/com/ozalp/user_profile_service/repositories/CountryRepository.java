package com.ozalp.user_profile_service.repositories;

import com.ozalp.user_profile_service.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {
    Optional<Country> findByName(String name);
}
