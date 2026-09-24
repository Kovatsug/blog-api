package com.api.blog.dto.response;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

public record ComentarioResponseDto(
        UUID id,
        LocalDate data,
        String comentario

) { }