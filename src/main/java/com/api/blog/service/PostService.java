package com.api.blog.service;

import com.api.blog.dto.request.ComentarioRequestDto;
import com.api.blog.dto.request.PostRequestDto;
import com.api.blog.dto.response.ComentarioResponseDto;
import com.api.blog.dto.response.PostResponseDto;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PostService {

    //Page<PostResponseDto> findAll(Pageable pageable);

    Page<PostResponseDto> findAll(Pageable pageable, String titulo);

    PostResponseDto findById(UUID id);

    PostResponseDto createPost(PostRequestDto dto);

    ComentarioResponseDto addComentario(UUID postId, ComentarioRequestDto dto);

}
