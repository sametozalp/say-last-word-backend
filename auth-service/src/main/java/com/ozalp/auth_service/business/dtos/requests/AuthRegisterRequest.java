package com.ozalp.auth_service.business.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRegisterRequest {
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email must be a valid email address")
    private String email;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotNull(message = "Country cannot be null")
    private CountryRequest country;

    @NotBlank(message = "Full name must not be empty")
    @Size(min = 3, message = "Full name be at least 3 characters long")
    private String fullName;

}
