package com.dev.blog.repository;

import com.dev.blog.model.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TagRepository extends JpaRepository<TagEntity, UUID> {

    @Query("SELECT t FROM tag t WHERE t.name LIKE %:name%")
    List<TagEntity> findByName(String name);
}
