package com.api.blog.mapper;

import com.api.blog.dto.request.PostRequestDto;
import com.api.blog.dto.response.ComentarioResponseDto;
import com.api.blog.dto.response.PostResponseDto;
import com.api.blog.model.ComentarioModel;
import com.api.blog.model.PostModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostMapper {

    private final ComentarioMapper comentarioMapper;

    public PostMapper(ComentarioMapper comentarioMapper){
        this.comentarioMapper = new ComentarioMapper();
    }

    public PostModel toEntity(PostRequestDto dto){
        if (dto == null) return null;

        return new PostModel(
                dto.autor(),
                dto.titulo(),
                dto.texto()
                );
    }

    public PostResponseDto toDto(PostModel entity){
        if (entity == null) return null;

        List<ComentarioResponseDto> comentariosDto = new ArrayList<>();
        if (entity.getComentarios() !=null){
            for (ComentarioModel comentario : entity.getComentarios()){
                ComentarioResponseDto dto = comentarioMapper.toDto(comentario);
                comentariosDto.add(dto);
            }
        }

        return new PostResponseDto(
                entity.getId(),
                entity.getAutor(),
                entity.getData(),
                entity.getTitulo(),
                entity.getTexto(),
                comentariosDto
        );
    }



}
