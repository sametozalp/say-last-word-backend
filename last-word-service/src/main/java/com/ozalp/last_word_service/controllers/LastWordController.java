package com.ozalp.last_word_service.controllers;

import com.ozalp.last_word_service.business.dtos.requests.LastWordRequest;
import com.ozalp.last_word_service.business.services.LastWordService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.UUID;

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

    @GetMapping(GET_LAST_WORD)
    ResponseEntity<?> getLastWord(Locale locale) {
        return ResponseEntity.ok(lastWordService.getLastWord(locale));
    }

    @GetMapping(GET_LAST_WORD_LIST)
    ResponseEntity<?> getLastWordList(Locale locale) {
        return ResponseEntity.ok(lastWordService.getLastWordList(locale));
    }

    @GetMapping(GET_LEADER_BOARD)
    ResponseEntity<?> getLeaderBoard(Locale locale) {
        return ResponseEntity.ok(lastWordService.getLeaderBoard(locale));
    }

    @GetMapping(BANNED + "/{wordId}")
    ResponseEntity<?> markAsBanned(@PathVariable(required = true) UUID wordId) {
        lastWordService.markAsBanned(wordId);
        return ResponseEntity.ok("");
    }
}
