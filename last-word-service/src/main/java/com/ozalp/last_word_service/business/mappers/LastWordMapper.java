package com.ozalp.last_word_service.business.mappers;

import com.ozalp.last_word_service.business.dtos.requests.LastWordRequest;
import com.ozalp.last_word_service.business.dtos.responses.LastWordResponse;
import com.ozalp.last_word_service.entities.LastWord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LastWordMapper {

    LastWord toEntity(LastWordRequest request);

    LastWordResponse toResponse(LastWord lastWord);

}
