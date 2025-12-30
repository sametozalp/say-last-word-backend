package com.ozalp.user_profile_service.controllers;

import com.ozalp.user_profile_service.business.dtos.requests.CreateUserProfileRequest;
import com.ozalp.user_profile_service.business.dtos.requests.UpdateUserProfileRequest;
import com.ozalp.user_profile_service.business.services.UserProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.ozalp.user_profile_service.util.Constants.*;

@RestController
@RequestMapping(USER_PROFILE + V1)
@AllArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(SAVE)
    ResponseEntity<?> save(@Valid @RequestBody CreateUserProfileRequest request) {
        return ResponseEntity.ok(userProfileService.save(request));
    }

    @PostMapping(UPDATE)
    ResponseEntity<?> update(@Valid @RequestBody UpdateUserProfileRequest request) {
        return ResponseEntity.ok(userProfileService.updateProfile(request));
    }

    @PostMapping(GET_PROFILE + "/{profileId}")
    ResponseEntity<?> getProfile(@PathVariable(required = true) UUID profileId) {
        return ResponseEntity.ok(userProfileService.getProfile(profileId));
    }
}
