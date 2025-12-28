package com.ozalp.user_profile_service.business.dtos.responses;

import com.ozalp.user_profile_service.entities.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class UserProfileResponse {

    private UUID id;

    private UUID authId;

    private String name;

    private CountryResponse country;
}
