package com.api.blog.repository;

import com.api.blog.Repository.PostRepository;
import com.api.blog.model.PostModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository repository;

    @Test
    void deveSalvarERetornarPost(){

        PostModel post = new PostModel("Henrique", "Alunos +devs", "Os alunos tem aura");

        PostModel postSalvo = repository.save(post);

        Assertions.assertThat(postSalvo).isNotNull();

    }

    @Test
    void deveListarPorTitulo(){

        PostModel post1 = new PostModel("Eu", "Minecraft vs Roblox", "Qual o melhor jogo de todos?");
        PostModel post2 = new PostModel("Isabelly", "Flor", "Sou uma flor");
        PostModel post3 = new PostModel("Wagner", "Minecraft", "minecraft é muito massa");
        PostModel post4 = new PostModel("Felipe", "Sabores", "Batata frita e pepino");
        PostModel post5 = new PostModel("Lucas", "Sistemas de roteirização", "Rotas para entregas");
        PostModel post6 = new PostModel("Mario", "Me", "Me, Mario");

        repository.save(post1);
        repository.save(post2);
        repository.save(post3);
        repository.save(post4);
        repository.save(post5);
        repository.save(post6);

        // Act
        Page<PostModel> listaPosts = repository.findByTituloContainingIgnoreCase("minecraft", PageRequest.of(0,10));


        Assertions.assertThat(listaPosts).hasSize(2);
    }

}
