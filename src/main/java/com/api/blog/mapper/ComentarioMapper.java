package com.api.blog.mapper;


import com.api.blog.dto.request.ComentarioRequestDto;
import com.api.blog.dto.response.ComentarioResponseDto;
import com.api.blog.model.ComentarioModel;
import com.api.blog.model.PostModel;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {

    public ComentarioModel toEntity(ComentarioRequestDto dto, PostModel entity){
        if (dto == null) return null;

        return new ComentarioModel(
                dto.comentario(),
                entity
        );
    }

    public ComentarioResponseDto toDto(ComentarioModel entity){
        if (entity == null) return null;

        return new ComentarioResponseDto(
                entity.getId(),
                entity.getData(),
                entity.getComentario()
        );
    }
}
