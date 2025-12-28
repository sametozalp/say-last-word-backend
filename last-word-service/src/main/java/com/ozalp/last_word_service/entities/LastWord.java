package com.ozalp.last_word_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "last_words")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LastWord extends BaseEntity {

    @Column(name = "user_profile_id", nullable = false)
    private UUID userProfileId;

    @Column(name = "text", nullable = false, length = 255)
    private String text;

    @Column(name = "is_banned", nullable = false)
    private boolean isBanned;
}
