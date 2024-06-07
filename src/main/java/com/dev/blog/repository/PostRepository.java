package com.dev.blog.repository;

import com.dev.blog.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
    @Query("SELECT p FROM post p WHERE p.title LIKE %:title%")
    List<PostEntity> findByTitle(String title);
}
