package com.api.blog.Repository;

import com.api.blog.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostModel, UUID> {

    @Query("""
        SELECT p FROM PostModel p
        WHERE LOWER(p.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))
    """)
    Page<PostModel> findByTituloContainingIgnoreCase(
            @Param("titulo") String titulo,
            Pageable pageable);
}