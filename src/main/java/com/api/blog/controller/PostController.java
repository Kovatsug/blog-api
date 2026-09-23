package com.api.blog.controller;

import com.api.blog.dto.request.PostRequestDto;
import com.api.blog.dto.response.PostResponseDto;
import com.api.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getAllPosts(){
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable UUID id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<PostResponseDto> newPost(@RequestBody PostRequestDto request){
        return ResponseEntity.status(201).body(postService.createPost(request));
    }

}
