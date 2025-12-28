package com.ozalp.user_profile_service.business.services;

import com.ozalp.user_profile_service.business.dtos.requests.CountryRequest;
import com.ozalp.user_profile_service.entities.Country;

public interface CountryService {
    Country save(CountryRequest request);

    Country getCountry(CountryRequest country);
}
