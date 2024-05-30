package com.dev.blog.controller;

import com.dev.blog.DTO.Tag.AddTagAtPostDTO;
import com.dev.blog.DTO.Tag.CreateTagDTO;
import com.dev.blog.model.TagEntity;
import com.dev.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public List<TagEntity> findAll(){
        return this.tagService.findAll();
    }

    @PostMapping("/")
    public TagEntity create(@RequestBody CreateTagDTO  createTagDTO){
        return this.tagService.create(createTagDTO);
    }

    @PostMapping("/add-tag-post")
    public TagEntity addTagAtPost(@RequestBody AddTagAtPostDTO addTagAtPostDTO){
        return this.tagService.addTagAtPost(addTagAtPostDTO);
    }

}
