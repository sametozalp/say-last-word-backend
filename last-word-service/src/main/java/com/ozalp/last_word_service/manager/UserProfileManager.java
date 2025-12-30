package com.ozalp.last_word_service.manager;

import com.ozalp.last_word_service.business.dtos.responses.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(url = "${user.profile.host}", name = "userProfileService")
public interface UserProfileManager {

    @PostMapping("/get-profile" + "/{profileId}")
    ResponseEntity<UserProfile> getProfile(@PathVariable(required = true) UUID profileId);
}
