package com.api.blog.service;

import com.api.blog.dto.request.PostRequestDto;
import com.api.blog.dto.response.PostResponseDto;
import com.api.blog.model.PostModel;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<PostResponseDto> findAll();

    PostResponseDto findById(UUID id);

    PostResponseDto createPost(PostRequestDto dto);

}
