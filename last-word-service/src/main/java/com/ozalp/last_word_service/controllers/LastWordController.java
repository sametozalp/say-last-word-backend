package com.ozalp.last_word_service.controllers;

import com.ozalp.last_word_service.business.dtos.requests.LastWordRequest;
import com.ozalp.last_word_service.business.services.LastWordService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

import static com.ozalp.last_word_service.util.Constants.*;

@RestController
@RequestMapping(LAST_WORD + V1)
@AllArgsConstructor
public class LastWordController {

    private final LastWordService lastWordService;

    @PostMapping(SAVE)
    ResponseEntity<?> save(@Valid @RequestBody LastWordRequest request) {
        return ResponseEntity.ok(lastWordService.save(request));
    }

    @PostMapping(GET_LAST_WORD)
    ResponseEntity<?> getLastWord(Locale locale) {
        return ResponseEntity.ok(lastWordService.getLastWord(locale));
    }

    @PostMapping(GET_LAST_WORD_LIST)
    ResponseEntity<?> getLastWordList(Locale locale) {
        return ResponseEntity.ok(lastWordService.getLastWordList(locale));
    }
}
