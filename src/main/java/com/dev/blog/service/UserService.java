package com.dev.blog.service;

import com.dev.blog.DTO.user.CreateUserDTO;
import com.dev.blog.model.UserEntity;
import com.dev.blog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity create(CreateUserDTO createUserDTO) {
        var userByEmail = this.findByEmail(createUserDTO.getEmail());

        if (userByEmail.isPresent()) {
            throw new NoSuchElementException("E-mail not available");
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(createUserDTO.getName());
        newUser.setEmail(createUserDTO.getEmail());

        return this.userRepository.save(newUser);
    }

    private Optional<UserEntity> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public UserEntity findById(UUID id) {
        var userById = this.userRepository.findById(id);
        if (!userById.isPresent()) {
            throw new NoSuchElementException("User not found");
        }

        return userById.get();
    }

    public List<UserEntity> findUsersByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

}