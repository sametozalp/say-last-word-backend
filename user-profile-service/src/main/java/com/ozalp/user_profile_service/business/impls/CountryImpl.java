package com.ozalp.user_profile_service.business.impls;

import com.ozalp.user_profile_service.business.dtos.requests.CountryRequest;
import com.ozalp.user_profile_service.business.mappers.CountryMapper;
import com.ozalp.user_profile_service.business.services.CountryService;
import com.ozalp.user_profile_service.entities.Country;
import com.ozalp.user_profile_service.repositories.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryImpl implements CountryService {

    private final CountryRepository repository;
    private final CountryMapper mapper;

    @Override
    public Country save(CountryRequest request) {
        return repository.save(mapper.toEntity(request));
    }

    @Override
    public Country getCountry(CountryRequest country) {
        Optional<Country> dbCountry = repository.findByName(country.getName());
        if (dbCountry.isPresent()) {
            return dbCountry.get();
        }
        return repository.save(mapper.toEntity(country));
    }
}
