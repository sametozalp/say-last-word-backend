package com.ozalp.user_profile_service.repositories;

import com.ozalp.user_profile_service.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
}
