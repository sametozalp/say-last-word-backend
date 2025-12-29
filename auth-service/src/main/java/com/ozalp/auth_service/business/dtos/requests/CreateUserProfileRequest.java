package com.ozalp.auth_service.business.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CreateUserProfileRequest {
    private UUID authId;
    private String fullName;
    private CountryRequest country;

}
