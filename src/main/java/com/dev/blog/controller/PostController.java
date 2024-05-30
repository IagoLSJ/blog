package com.dev.blog.controller;

import com.dev.blog.DTO.post.CreatePostDTO;
import com.dev.blog.model.PostEntity;
import com.dev.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping("/")
    public List<PostEntity> findAll(){
        return this.postService.findAll();
    }

    @PostMapping("/")
    public PostEntity create(@RequestBody CreatePostDTO createPostDTO){
        return this.postService.create(createPostDTO);
    }


}
