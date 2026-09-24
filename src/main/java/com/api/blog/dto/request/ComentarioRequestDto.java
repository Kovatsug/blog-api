package com.api.blog.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ComentarioRequestDto(
        @NotBlank(message = "O comentario não pode ser vazio")
        String comentario
) {
}
