package com.api.blog.teste.context;

public record ContextoResponseDto(
        String locale,
        String timezone,
        String data,
        String moeda,
        String numero
) {
}
