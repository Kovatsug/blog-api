package com.api.blog.repository;

import com.api.blog.Repository.PostRepository;
import com.api.blog.model.PostModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

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

}
