package com.dev.blog.controller;

import com.dev.blog.DTO.postComment.CreatePostCommentDTO;
import com.dev.blog.DTO.postComment.ReturnPostCommentDTO;
import com.dev.blog.model.PostCommentEntity;
import com.dev.blog.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class PostCommentController {

    @Autowired
    private PostCommentService postCommentService;

    @PostMapping("/")
    public ReturnPostCommentDTO create(@RequestBody CreatePostCommentDTO createPostCommentDTO){
        return this.postCommentService.create(createPostCommentDTO);
    }

}
