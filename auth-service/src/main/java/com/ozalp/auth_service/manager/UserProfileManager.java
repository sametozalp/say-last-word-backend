package com.ozalp.auth_service.manager;

import com.ozalp.auth_service.business.dtos.requests.CreateUserProfileRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${user.profile.host}", name = "userProfileService")
public interface UserProfileManager {

    @PostMapping("/save")
    ResponseEntity<?> save(@Valid @RequestBody CreateUserProfileRequest request);
}
