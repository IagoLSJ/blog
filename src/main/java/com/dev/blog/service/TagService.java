package com.dev.blog.service;

import com.dev.blog.DTO.Tag.AddTagAtPostDTO;
import com.dev.blog.DTO.Tag.CreateTagDTO;
import com.dev.blog.model.TagEntity;
import com.dev.blog.repository.PostRepository;
import com.dev.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostRepository postRepository;


    public TagEntity create(CreateTagDTO createTagDTO){
        TagEntity newTag = new TagEntity();

        newTag.setName(createTagDTO.getName());

        return this.tagRepository.save(newTag);
    }

    public TagEntity addTagAtPost(AddTagAtPostDTO addTagAtPostDTO){
        var postById = this.postRepository.findById(addTagAtPostDTO.getPostId());
        var tagById = this.tagRepository.findById(addTagAtPostDTO.getTagId());

        if(!postById.isPresent()){
            throw new NoSuchElementException("Post not exist");
        }

        if(!tagById.isPresent()){
            throw new NoSuchElementException("Tag not exist");
        }

        postById.get().getTags().add(tagById.get());

        this.postRepository.save(postById.get());

        tagById.get().getPosts().add(postById.get());

        return this.tagRepository.save(tagById.get());
    }

    public List<TagEntity> findAll(){
        return this.tagRepository.findAll();
    }
}
