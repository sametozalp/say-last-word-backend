package com.ozalp.last_word_service.business.services;

import com.ozalp.last_word_service.business.dtos.requests.LastWordAnonymousRequest;
import com.ozalp.last_word_service.business.dtos.requests.LastWordRequest;
import com.ozalp.last_word_service.business.dtos.responses.LastWordResponse;
import com.ozalp.last_word_service.entities.LastWord;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

public interface LastWordService {

    LastWordResponse save(LastWordRequest request);

    LastWordResponse save(LastWordAnonymousRequest request);

    LastWordResponse getLastWord(Locale locale);

    List<LastWordResponse> getLastWordList(Locale locale);

    List<LastWordResponse> getLeaderBoard(Locale locale);

    void markAsBanned(UUID wordId);
}
