package com.ozalp.user_profile_service.business.impls;

import com.ozalp.user_profile_service.business.dtos.requests.CreateUserProfileRequest;
import com.ozalp.user_profile_service.business.dtos.requests.UpdateUserProfileRequest;
import com.ozalp.user_profile_service.business.dtos.responses.UserProfileResponse;
import com.ozalp.user_profile_service.business.mappers.CountryMapper;
import com.ozalp.user_profile_service.business.mappers.UserProfileMapper;
import com.ozalp.user_profile_service.business.services.CountryService;
import com.ozalp.user_profile_service.business.services.UserProfileService;
import com.ozalp.user_profile_service.entities.Country;
import com.ozalp.user_profile_service.entities.UserProfile;
import com.ozalp.user_profile_service.repositories.UserProfileRepository;
import com.ozalp.user_profile_service.util.Messages;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserProfileImpl implements UserProfileService {

    private final UserProfileRepository repository;
    private final UserProfileMapper userProfileMapper;
    private final CountryMapper countryMapper;
    private final CountryService countryService;

//    @Override
//    public UserProfileResponse updateProfile(UpdateUserProfileRequest request) {
//        UserProfile userProfile = findById(request.getId());
//        userProfile.setName(request.getName());
//        Country country = new Country(request.getCountry().getName(), request.getCountry().getFlagUrl());
//
//        userProfile.setCountry();
//        UserProfile saved = repository.save(userProfile);
//
//        return UserProfileResponse.builder()
//                .name(saved.getName())
//                .country(new CountryResponse(saved.getCountry().getName(), saved.getCountry().getFlagUrl()))
//                .build();
//    }

    private UserProfile findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.ProfileUser.NOT_FOUND));
    }

    @Override
    public UserProfileResponse updateProfile(UpdateUserProfileRequest request) {
        UserProfile userProfile = findById(request.getId());
        userProfile.setFullName(request.getFullName());
        Country country = countryService.getCountry(request.getCountry());
        userProfile.setCountry(country);
        return userProfileMapper.toResponse(repository.save(userProfile));
    }

    @Override
    public UserProfileResponse save(CreateUserProfileRequest request) {
        UserProfile userProfile = new UserProfile(
                request.getAuthId(),
                request.getFullName(),
                countryService.save(request.getCountry())
        );
        UserProfile saved = repository.save(userProfile);
        return UserProfileResponse.builder()
                .id(saved.getId())
                .authId(saved.getAuthId())
                .name(request.getFullName())
                .build();
    }

}
