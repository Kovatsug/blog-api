package com.api.blog.Repository;

import com.api.blog.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostModel, UUID> {

    Page<PostModel> findByTituloContainigIgnoreCase(
            String titulo,
            Pageable pageable);

}