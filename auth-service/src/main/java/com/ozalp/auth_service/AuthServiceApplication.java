package com.ozalp.auth_service;

import com.ozalp.auth_service.business.enums.RoleEnum;
import com.ozalp.auth_service.business.services.AuthService;
import com.ozalp.auth_service.entities.Auth;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableCaching
@AllArgsConstructor
@EnableFeignClients
public class AuthServiceApplication implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (!authService.existsAdmin()) {
            Auth auth = new Auth("sametozalp0056@gmail.com", "admin", passwordEncoder.encode("adminsamet"), true, RoleEnum.ADMIN);
            authService.save(auth);
        }
    }
}