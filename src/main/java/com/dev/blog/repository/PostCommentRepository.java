package com.dev.blog.repository;

import com.dev.blog.model.PostCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostCommentRepository extends JpaRepository<PostCommentEntity, UUID> {
}
