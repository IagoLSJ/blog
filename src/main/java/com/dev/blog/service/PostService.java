package com.dev.blog.service;

import com.dev.blog.DTO.post.CreatePostDTO;
import com.dev.blog.configs.ResourceNotFoundException;
import com.dev.blog.model.PostEntity;
import com.dev.blog.repository.PostRepository;
import com.dev.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    public List<PostEntity> findAll(){
        return this.postRepository.findAll();
    }

    public PostEntity findById(UUID id){
        var postById = this.postRepository.findById(id);

        if(!postById.isPresent()){
            throw new ResourceNotFoundException("Post not exist");
        }

        return postById.get();
    }

    public PostEntity create(CreatePostDTO createPostDTO){
        PostEntity newPost = new PostEntity();

        newPost.setTitle(createPostDTO.getTitle());
        newPost.setContent(createPostDTO.getContent());
        newPost.setCreatedOn(new Date());

        return this.postRepository.save(newPost);
    }

    public List<PostEntity> findByTitle(String title){
        return this.postRepository.findByTitle(title);
    }
}
