package com.ozalp.user_profile_service.business.mappers;

import com.ozalp.user_profile_service.business.dtos.requests.CountryRequest;
import com.ozalp.user_profile_service.business.dtos.responses.CountryResponse;
import com.ozalp.user_profile_service.entities.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    Country toEntity(CountryRequest request);

    CountryResponse toResponse(Country country);
}
