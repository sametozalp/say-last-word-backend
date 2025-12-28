package com.ozalp.last_word_service.repositories;

import com.ozalp.last_word_service.entities.LastWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LastWordRepository extends JpaRepository<LastWord, UUID> {

    Optional<LastWord> findFirstByDeletedAtIsNullAndIsBannedIsFalseOrderByCreatedAtDesc();

}
