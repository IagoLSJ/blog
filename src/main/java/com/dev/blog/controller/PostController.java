package com.dev.blog.controller;

import com.dev.blog.DTO.post.CreatePostDTO;
import com.dev.blog.model.PostEntity;
import com.dev.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping("/")
    public List<PostEntity> findAll(){
        return this.postService.findAll();
    }
    @GetMapping("/{id}")
    public PostEntity findById(@PathVariable UUID id){
        return this.postService.findById(id);
    }

    @GetMapping("/searchByTitle/{title}")
    public List<PostEntity> findByTitle(@PathVariable String title){
        return this.postService.findByTitle(title);
    }

    @PostMapping("/")
    public PostEntity create(@RequestBody CreatePostDTO createPostDTO){
        return this.postService.create(createPostDTO);
    }


}
