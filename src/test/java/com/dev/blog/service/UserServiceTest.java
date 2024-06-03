package com.dev.blog.service;

import com.dev.blog.DTO.user.CreateUserDTO;
import com.dev.blog.configs.ResourceNotFoundException;
import com.dev.blog.model.UserEntity;
import com.dev.blog.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    UserEntity userEntity;
    UUID userId = UUID.randomUUID();

    @BeforeEach
    public void setUp() {
        userEntity = new UserEntity();
        userEntity.setUsername("Iago");
        userEntity.setEmail("Iago@gmail.com");
        userEntity.setId(userId);
    }

    @Test
    void createUser_Success() {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setName("Iago");
        createUserDTO.setEmail("Iago@gmail.com");

        when(userRepository.findByEmail("Iago@gmail.com")).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(userEntity);

        UserEntity createdUser = userService.create(createUserDTO);

        assertEquals("Iago", createdUser.getUsername());
        assertEquals("Iago@gmail.com", createdUser.getEmail());

        verify(userRepository).findByEmail("Iago@gmail.com");

        verify(userRepository).save(any());

        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void createUser_EmailAlreadyExists() {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setName("John Doe");
        createUserDTO.setEmail("Iago@gmail.com");

        when(userRepository.findByEmail("Iago@gmail.com")).thenReturn(Optional.of(userEntity));

        assertThrows(ResourceNotFoundException.class, () -> userService.create(createUserDTO));

        verify(userRepository).findByEmail("Iago@gmail.com");

        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void findById_Success() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        UserEntity foundUser = userService.findById(userId);

        assertEquals(userEntity, foundUser);

        verify(userRepository).findById(userId);

        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void findById_UserNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.findById(userId));

        verify(userRepository).findById(userId);

        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void findUsersByUsername_Success() {
        String username = "Iago";

        when(userRepository.findByUsername(username)).thenReturn(List.of(userEntity));

        List<UserEntity> foundUsers = userService.findUsersByUsername(username);

        assertFalse(foundUsers.isEmpty());
        assertEquals(userEntity, foundUsers.get(0));

        verify(userRepository).findByUsername(username);

        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void findUsersByUsername_NoUsersFound() {
        String username = "John";

        when(userRepository.findByUsername(username)).thenReturn(new ArrayList<>());

        List<UserEntity> foundUsers = userService.findUsersByUsername(username);

        assertTrue(foundUsers.isEmpty());

        verify(userRepository).findByUsername(username);

        verifyNoMoreInteractions(userRepository);
    }
}
