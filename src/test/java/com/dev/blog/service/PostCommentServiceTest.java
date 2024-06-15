package com.dev.blog.service;

import com.dev.blog.DTO.postComment.CreatePostCommentDTO;
import com.dev.blog.DTO.postComment.ReturnPostCommentDTO;
import com.dev.blog.configs.ResourceNotFoundException;
import com.dev.blog.model.PostCommentEntity;
import com.dev.blog.model.PostEntity;
import com.dev.blog.repository.PostCommentRepository;
import com.dev.blog.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostCommentServiceTest {

    @InjectMocks
    PostCommentService postCommentService;

    @Mock
    PostCommentRepository postCommentRepository;

    @Mock
    PostRepository postRepository;

    PostCommentEntity postComment;
    PostEntity postEntity;
    UUID postId = UUID.randomUUID();
    UUID postCommentId = UUID.randomUUID();
    @BeforeEach
    void setUp() {
        postEntity = new PostEntity();
        postEntity.setId(postId);
        postEntity.setTitle("Test Post");

        postComment = new PostCommentEntity();
        postComment.setId(postCommentId);
        postComment.setPost(postEntity);
        postComment.setReview("Test Review");
    }

    @Test
    void createPostComment_Success() {
        CreatePostCommentDTO createPostCommentDTO = new CreatePostCommentDTO();
        createPostCommentDTO.setPostId(postEntity.getId());
        createPostCommentDTO.setReview("Test Review");

        when(postRepository.findById(postEntity.getId())).thenReturn(Optional.of(postEntity));
        when(postCommentRepository.save(any(PostCommentEntity.class))).thenReturn(postComment);

        ReturnPostCommentDTO createdPostCommentDTO = postCommentService.create(createPostCommentDTO);

        assertNotNull(createdPostCommentDTO);
        assertEquals(postComment.getId(), createdPostCommentDTO.getId());
        assertEquals(postComment.getPost(), createdPostCommentDTO.getPost());
        assertEquals(postComment.getReview(), createdPostCommentDTO.getReview());
        assertEquals(postComment.getVotes(), createdPostCommentDTO.getVotes());

        verify(postRepository).findById(postEntity.getId());
        verify(postCommentRepository).save(any(PostCommentEntity.class));
    }

    @Test
    void createPostComment_PostNotFound() {
        CreatePostCommentDTO createPostCommentDTO = new CreatePostCommentDTO();
        createPostCommentDTO.setPostId(postEntity.getId());
        createPostCommentDTO.setReview("Test Review");

        when(postRepository.findById(postEntity.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> postCommentService.create(createPostCommentDTO));

        verify(postRepository).findById(postEntity.getId());
        verifyNoInteractions(postCommentRepository);
    }
}
