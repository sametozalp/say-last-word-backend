package com.ozalp.user_profile_service.business.services;

import com.ozalp.user_profile_service.business.dtos.requests.UpdateUserProfileRequest;
import com.ozalp.user_profile_service.business.dtos.responses.UserProfileResponse;

import java.util.UUID;

public interface UserProfileService {

    UserProfileResponse updateProfile(UpdateUserProfileRequest request);

    UserProfileResponse save(UUID authId);

}
