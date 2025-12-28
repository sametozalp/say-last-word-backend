package com.ozalp.last_word_service.business.services;

import com.ozalp.last_word_service.business.dtos.requests.LastWordRequest;
import com.ozalp.last_word_service.business.dtos.responses.LastWordResponse;

import java.util.List;
import java.util.Locale;

public interface LastWordService {

    LastWordResponse save(LastWordRequest request);

    LastWordResponse getLastWord(Locale locale);

    List<LastWordResponse> getLastWordList(Locale locale);

}
