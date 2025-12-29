package com.ozalp.last_word_service.business.impls;

import com.ozalp.last_word_service.business.dtos.requests.LastWordAnonymousRequest;
import com.ozalp.last_word_service.business.dtos.requests.LastWordRequest;
import com.ozalp.last_word_service.business.dtos.responses.LastWordResponse;
import com.ozalp.last_word_service.business.mappers.LastWordMapper;
import com.ozalp.last_word_service.business.services.LastWordService;
import com.ozalp.last_word_service.entities.LastWord;
import com.ozalp.last_word_service.exceptions.errors.AlreadySaidYourLastWord;
import com.ozalp.last_word_service.repositories.LastWordRepository;
import com.ozalp.last_word_service.util.Messages;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LastWordImpl implements LastWordService {

    private final LastWordRepository repository;
    private final LastWordMapper mapper;

    @Transactional
    @Override
    public LastWordResponse save(LastWordRequest request) {
        // öncekinin süresi doldu
        Optional<LastWord> oldWord = repository.findFirstByDeletedAtIsNullAndIsBannedIsFalseOrderByCreatedAtDesc();
        if (oldWord.isPresent()) {
            LastWord old = oldWord.get();

            if (old.getUserProfileId() != null && old.getUserProfileId().equals(request.getUserProfileId())) {
                throw new AlreadySaidYourLastWord(Messages.LastWord.ALREADY_SAID);
            }

            old.setExpiredTime(LocalDateTime.now());
            repository.save(old);
        }

        // yeni kelimeyi ekle
        LastWord newWord = mapper.toEntity(request);
        newWord.setBanned(false);
        LastWord saved = repository.save(newWord);
        return LastWordResponse.builder()
                .id(saved.getId())
                .text(saved.getText())
                .userProfileId(saved.getUserProfileId())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    @Override
    public LastWordResponse save(LastWordAnonymousRequest request) {
        // öncekinin süresi doldu
        Optional<LastWord> oldWord = repository.findFirstByDeletedAtIsNullAndIsBannedIsFalseOrderByCreatedAtDesc();
        if (oldWord.isPresent()) {
            LastWord old = oldWord.get();
            old.setExpiredTime(LocalDateTime.now());
            repository.save(old);
        }

        // yeni kelimeyi ekle
        LastWord newWord = mapper.toEntity(request);
        newWord.setBanned(false);
        LastWord saved = repository.save(newWord);
        return LastWordResponse.builder()
                .id(saved.getId())
                .text(saved.getText())
                .userProfileId(saved.getUserProfileId())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    @Override
    public LastWordResponse getLastWord(Locale locale) {
        LastWord lastWord = repository.findFirstByDeletedAtIsNullAndIsBannedIsFalseOrderByCreatedAtDesc()
                .orElseThrow(() -> new EntityNotFoundException(Messages.LastWord.NOT_FOUND));
        return LastWordResponse.builder()
                .id(lastWord.getId())
                .text(lastWord.getText())
                .userProfileId(lastWord.getUserProfileId())
                .createdAt(lastWord.getCreatedAt())
                .timeElapsed(formatElapsedTime(lastWord.getCreatedAt(), locale))
                .build();
    }

    @Override
    public List<LastWordResponse> getLastWordList(Locale locale) {
        List<LastWord> lastWordList = repository.findByDeletedAtIsNullAndIsBannedIsFalseOrderByCreatedAtDesc();
        return lastWordList.stream()
                .map(l -> {
                    LastWordResponse response = mapper.toResponse(l);
                    response.setCreatedAt(l.getCreatedAt());
                    response.setTimeElapsed(formatElapsedTime(l.getCreatedAt(), locale));
                    return response;
                })
                .toList();
    }

    @Override
    public List<LastWordResponse> getLeaderBoard(Locale locale) {
        Page<LastWord> result = repository.findTop5ByMaxTimeDiff(PageRequest.of(0, 5));
        return result.map(l -> {
                            LastWordResponse response = mapper.toResponse(l);
                            response.setCreatedAt(l.getCreatedAt());
                            response.setTimeElapsed(formatElapsedTime(l.getCreatedAt(), locale));
                            return response;
                        }
                )
                .stream()
                .toList();
    }

    @Override
    public void markAsBanned(UUID wordId) {
        LastWord word = repository.findById(wordId)
                .orElseThrow(() -> new RuntimeException("Kelime bulunamadı."));
        word.setBanned(true);
        repository.save(word);
    }

    private String formatElapsedTime(LocalDateTime createdAt, Locale locale) {
        Duration duration = Duration.between(createdAt, LocalDateTime.now());

        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;

        if (locale.getLanguage().equals("tr")) {
            return String.format("%d gün %d saat %d dakika", days, hours, minutes);
        } else {
            return String.format("%d days %d hours %d minutes", days, hours, minutes);
        }
    }
}
