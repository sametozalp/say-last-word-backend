package com.ozalp.last_word_service.business.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class UserProfile {

    private UUID id;

    private UUID authId;

    private String fullName;

    private Country country;
}
