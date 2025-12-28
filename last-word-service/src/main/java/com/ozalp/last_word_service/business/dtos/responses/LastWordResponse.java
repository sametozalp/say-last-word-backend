package com.ozalp.last_word_service.business.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class LastWordResponse {

    private UUID id;

    private UUID userProfileId;

    private String text;

    private LocalDateTime createdAt;

    private String timeElapsed;

}
