package com.api.blog.dto.response;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.UUID;

public record PostResponseDto(
        UUID id,
        String autor,
        LocalDate data,
        String titulo,
        String texto
) {
}
