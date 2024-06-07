package com.dev.blog.service;

import com.dev.blog.DTO.post.CreatePostDTO;
import com.dev.blog.configs.ResourceNotFoundException;
import com.dev.blog.model.PostEntity;
import com.dev.blog.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks
    PostService postService;

    @Mock
    PostRepository postRepository;

    PostEntity post;
    UUID postId = UUID.randomUUID();

    @BeforeEach
    public void setUp(){
        post = new PostEntity();
        post.setId(postId);
        post.setTitle("A fome");
        post.setContent("A fome no mundo");
        post.setCreatedOn(new Date());
    }

    @Test
    void findById_Sucess(){
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        PostEntity foundPost = postService.findById(postId);

        assertEquals(post, foundPost);

        verify(postRepository).findById(postId);

        verifyNoMoreInteractions(postRepository);
    }

    @Test
    void findById_NotFound(){
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> postService.findById(postId));

        verify(postRepository).findById(postId);

        verifyNoMoreInteractions(postRepository);
    }

    @Test
    void findAll(){
        when(postRepository.findAll()).thenReturn(List.of(post));

        List<PostEntity> foundAllPosts = postService.findAll();

        assertEquals(post, foundAllPosts.get(0));

        verify(postRepository).findAll();

        verifyNoMoreInteractions(postRepository);
    }

    @Test
    void findByTitle_Sucess(){
        when(postRepository.findByTitle("A fome")).thenReturn(List.of(post));

        List<PostEntity> foundPostByTitle = postService.findByTitle("A fome");

        assertEquals(post, foundPostByTitle.get(0));

        verify(postRepository).findByTitle("A fome");

        verifyNoMoreInteractions(postRepository);
    }

    @Test
    void findByTitle_NotFound(){
        when(postRepository.findByTitle("Morte")).thenReturn(new ArrayList<>());

        List<PostEntity> foundPostByTitle = postService.findByTitle("Morte");

        assertTrue(foundPostByTitle.isEmpty());

        verify(postRepository).findByTitle("Morte");

        verifyNoMoreInteractions(postRepository);
    }

    @Test
    void createPost(){
        CreatePostDTO dto = new CreatePostDTO();

        dto.setTitle("A fome");
        dto.setContent("A fome no mundo");

        when(postRepository.save(any())).thenReturn(post);

        PostEntity createdPost = postService.create(dto);

        assertEquals("A fome", createdPost.getTitle());
        assertEquals("A fome no mundo", createdPost.getContent());

        verify(postRepository).save(any());

        verifyNoMoreInteractions(postRepository);
    }





}
