package com.ozalp.user_profile_service.business.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryRequest {

    private String name;

    private String flagUrl;
}
