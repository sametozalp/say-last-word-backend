package com.ozalp.last_word_service.business.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LastWordAnonymousRequest {

    @NotBlank(message = "Text cannot be blank")
    @NotNull(message = "Text cannot be null")
    @Size(min = 10, max = 255, message = "Metin 10 ile 255 karakter arasında olmalıdır")
    private String text;
}
