package com.ozalp.auth_service;

import com.ozalp.auth_service.business.enums.RoleEnum;
import com.ozalp.auth_service.business.services.AuthService;
import com.ozalp.auth_service.entities.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableCaching
@EnableFeignClients
public class AuthServiceApplication implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    public AuthServiceApplication(PasswordEncoder passwordEncoder, AuthService authService) {
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (!authService.existsAdmin()) {
            Auth auth = new Auth(adminEmail, adminUsername, passwordEncoder.encode(adminPassword), true, RoleEnum.ADMIN);
            authService.save(auth);
        }
    }
}