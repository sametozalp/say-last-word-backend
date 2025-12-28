package com.ozalp.user_profile_service.business.mappers;

import com.ozalp.user_profile_service.business.dtos.requests.UpdateUserProfileRequest;
import com.ozalp.user_profile_service.business.dtos.responses.UserProfileResponse;
import com.ozalp.user_profile_service.entities.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toEntity(UpdateUserProfileRequest request);

    UserProfileResponse toResponse(UserProfile country);
}
