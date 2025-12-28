package com.ozalp.auth_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshToken extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "auth_id", nullable = false)
    private Auth auth;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name = "expiration", nullable = false)
    private LocalDateTime expiration;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiration);
    }
}
