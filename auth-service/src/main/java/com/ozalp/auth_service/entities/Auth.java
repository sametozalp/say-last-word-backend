package com.ozalp.auth_service.entities;

import com.ozalp.auth_service.business.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "auths")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auth extends BaseEntity {
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}
