package com.ozalp.user_profile_service.business.services;

import com.ozalp.user_profile_service.business.dtos.requests.CreateUserProfileRequest;
import com.ozalp.user_profile_service.business.dtos.requests.UpdateUserProfileRequest;
import com.ozalp.user_profile_service.business.dtos.responses.UserProfileResponse;

public interface UserProfileService {

    UserProfileResponse updateProfile(UpdateUserProfileRequest request);

    UserProfileResponse save(CreateUserProfileRequest request);

}
