package com.ozalp.last_word_service.business.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LastWordRequest {

    private UUID userProfileId;

    private String text;
}
