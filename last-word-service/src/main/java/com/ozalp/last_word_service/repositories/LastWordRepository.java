package com.ozalp.last_word_service.repositories;

import com.ozalp.last_word_service.entities.LastWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LastWordRepository extends JpaRepository<LastWord, UUID> {

    Optional<LastWord> findFirstByDeletedAtIsNullAndIsBannedIsFalseOrderByCreatedAtDesc();

    List<LastWord> findByDeletedAtIsNullAndIsBannedIsFalseOrderByCreatedAtDesc();

    @Query("""
                SELECT e
                FROM LastWord e
                WHERE e.isBanned = false
                ORDER BY (COALESCE(e.expiredTime, CURRENT_TIMESTAMP) - e.createdAt) DESC
            """)
    Page<LastWord> findTop5ByMaxTimeDiff(Pageable pageable);

}
