package com.dev.blog.service;

import com.dev.blog.DTO.post.CreatePostDTO;
import com.dev.blog.model.PostEntity;
import com.dev.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    public PostEntity create(CreatePostDTO createPostDTO){
        PostEntity newPost = new PostEntity();

        newPost.setTitle(createPostDTO.getTitle());
        newPost.setContent(createPostDTO.getContent());
        newPost.setCreatedOn(new Date());

        return this.postRepository.save(newPost);
    }

    public List<PostEntity> findAll(){
        return this.postRepository.findAll();
    }

}
