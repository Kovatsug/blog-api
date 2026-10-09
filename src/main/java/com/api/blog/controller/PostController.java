package com.api.blog.controller;

import com.api.blog.dto.request.ComentarioRequestDto;
import com.api.blog.dto.request.PostRequestDto;
import com.api.blog.dto.response.ComentarioResponseDto;
import com.api.blog.dto.response.PostResponseDto;
import com.api.blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Post e comentários", description = "Operações do Blog API")
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Lista posts com paginação")
    @GetMapping("/posts")
    public ResponseEntity<Page<PostResponseDto>> getAllPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "data") String sortBy, @RequestParam(defaultValue = "desc" ) String direction, @RequestParam(required = false) String titulo){

        Sort sort = direction.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page,size,sort);
        return ResponseEntity.ok(postService.findAll(pageable, titulo));
    }

    @Operation(summary = "Retorna post pelo id")
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable UUID id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @Operation(summary = "Criar post")
    @PostMapping("/newpost")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody @Valid PostRequestDto request){
        PostResponseDto created = postService.createPost(request);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Adiciona comentario à um post")
    @PostMapping("/comentarios/{postId}")
    public ResponseEntity<ComentarioResponseDto> createComentario(@PathVariable UUID postId, @RequestBody @Valid ComentarioRequestDto dto){
        return ResponseEntity.status(201).body(postService.addComentario(postId,dto));
    }

}
