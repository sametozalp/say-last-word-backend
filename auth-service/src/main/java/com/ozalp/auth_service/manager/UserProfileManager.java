package com.ozalp.auth_service.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(url = "${user.profile.host}", name = "userProfileService")
public interface UserProfileManager {

    @PostMapping("/save")
    ResponseEntity<?> save(UUID authId);
}
