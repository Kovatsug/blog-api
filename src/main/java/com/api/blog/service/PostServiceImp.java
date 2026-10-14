package com.api.blog.service;

import com.api.blog.Repository.ComentarioRepository;
import com.api.blog.Repository.PostRepository;
import com.api.blog.dto.request.ComentarioRequestDto;
import com.api.blog.dto.request.PostRequestDto;
import com.api.blog.dto.response.ComentarioResponseDto;
import com.api.blog.dto.response.PostResponseDto;
import com.api.blog.mapper.ComentarioMapper;
import com.api.blog.mapper.PostMapper;
import com.api.blog.model.ComentarioModel;
import com.api.blog.model.PostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;
    private final MessageService messageService;

    @Autowired
    public PostServiceImp(PostRepository postRepository, PostMapper postMapper, ComentarioRepository comentarioRepository, ComentarioMapper comentarioMapper, MessageService messageService) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.comentarioRepository = comentarioRepository;
        this.comentarioMapper = comentarioMapper;
        this.messageService = messageService;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostResponseDto> findAll(Pageable pageable, String titulo) {
        Page<PostModel> posts;

        if (titulo == null || titulo.isBlank()) {
            posts = postRepository.findAll(pageable);
        } else {
            posts = postRepository.findByTituloContainingIgnoreCase(titulo, pageable);
        }

        return posts.map(postMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostResponseDto> findAll(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(postMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDto findById(UUID id) {
        PostModel post = postRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                messageService.getMessage("post.nao.encontrado", id)));

        return postMapper.toDto(post);
    }

    @Override
    @Transactional
    public PostResponseDto createPost(PostRequestDto dto) {
        PostModel post = postMapper.toEntity(dto);
        PostModel saved = postRepository.save(post);
        return postMapper.toDto(saved);
    }

    @Override
    @Transactional
    public ComentarioResponseDto addComentario(UUID postId, ComentarioRequestDto dto){
        PostModel post = postRepository.findById(postId)
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                messageService.getMessage("post.nao.encontrado", postId)));

        ComentarioModel comentario = new ComentarioModel(dto.comentario(), post);

        post.adicionarComentario(comentario);
        ComentarioModel saved = comentarioRepository.save(comentario);
        return comentarioMapper.toDto(saved);
    }

    @Override
    @Transactional
    public PostResponseDto updatePost(UUID id, PostRequestDto dto) {
        PostModel post = postRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                messageService.getMessage("post.nao.encontrado", id)));

        post.setAutor(dto.autor());
        post.setTitulo(dto.titulo());
        post.setTexto(dto.texto());

        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    @Transactional
    public void deletePost(UUID id) {
        PostModel post = postRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                messageService.getMessage("post.nao.encontrado", id)));

        postRepository.delete(post);
    }

    @Override
    @Transactional
    public ComentarioResponseDto updateComentario(
            UUID postId, UUID comentarioId, ComentarioRequestDto dto) {
        postRepository.findById(postId)
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                messageService.getMessage("post.nao.encontrado", postId)));

        ComentarioModel comentario = comentarioRepository.findById(comentarioId)
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                messageService.getMessage("comentario.nao.encontrado", comentarioId)));

        if (!comentario.getPost().getId().equals(postId)) {
            throw new RecursoNaoEncontradoException(
                messageService.getMessage("comentario.nao.encontrado.no.post", comentarioId, postId));
        }

        comentario.setComentario(dto.comentario());
        return comentarioMapper.toDto(comentarioRepository.save(comentario));
    }

    @Override
    @Transactional
    public void deleteComentario(UUID postId, UUID comentarioId) {
        postRepository.findById(postId)
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                messageService.getMessage("post.nao.encontrado", postId)));

        ComentarioModel comentario = comentarioRepository.findById(comentarioId)
            .orElseThrow(() -> new RecursoNaoEncontradoException(
                messageService.getMessage("comentario.nao.encontrado", comentarioId)));

        if (!comentario.getPost().getId().equals(postId)) {
            throw new RecursoNaoEncontradoException(
                messageService.getMessage("comentario.nao.encontrado.no.post", comentarioId, postId));
        }

        comentarioRepository.delete(comentario);
    }

}
