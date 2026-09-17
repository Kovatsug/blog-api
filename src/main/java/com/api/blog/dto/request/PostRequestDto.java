package com.api.blog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequestDto(
        @NotBlank(message = "O autor é obrigatório")
        @Size(max = 70,message = "Autor deve ter um máximo de 70 caracteres")
        String autor,

        @NotBlank(message = "Titulo obrigatorio")
        @Size(max = 100, message = "Titulo maximo de 100 caracteres")
        String titulo,

        @NotBlank(message = "O texto é obrigatório")
        String texto
) {
}
