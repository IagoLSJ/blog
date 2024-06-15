package com.dev.blog.repository;

import com.dev.blog.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);
    @Query("SELECT u FROM users u WHERE u.username LIKE %:username%")
    List<UserEntity> findByUsername(String username);
}
