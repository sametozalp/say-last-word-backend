package com.ozalp.user_profile_service.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountryResponse {

    private String name;

    private String flagUrl;
}
