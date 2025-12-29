package com.ozalp.user_profile_service.business.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateUserProfileRequest {
    private UUID authId;
    private String fullName;
    private CountryRequest country;

}
