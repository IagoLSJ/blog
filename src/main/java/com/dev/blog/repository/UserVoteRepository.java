package com.dev.blog.repository;

import com.dev.blog.model.UserVoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserVoteRepository extends JpaRepository<UserVoteEntity, UUID> {
}
