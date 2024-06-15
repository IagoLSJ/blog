package com.dev.blog.controller;

import com.dev.blog.DTO.Tag.AddTagAtPostDTO;
import com.dev.blog.DTO.Tag.CreateTagDTO;
import com.dev.blog.DTO.Tag.UpdateTagDTO;
import com.dev.blog.model.TagEntity;
import com.dev.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public List<TagEntity> findAll(){
        return this.tagService.findAll();
    }

    @GetMapping("/{name}")
    public List<TagEntity> findByName(@PathVariable String name){
        return this.tagService.findByName(name);
    }

    @PostMapping("/")
    public TagEntity create(@RequestBody CreateTagDTO  createTagDTO){
        return this.tagService.create(createTagDTO);
    }

    @PostMapping("/add-tag-post")
    public TagEntity addTagAtPost(@RequestBody AddTagAtPostDTO addTagAtPostDTO){
        return this.tagService.addTagAtPost(addTagAtPostDTO);
    }

    @PutMapping("/{id}")
    public TagEntity update(@PathVariable UUID id, @RequestBody UpdateTagDTO updateTagDTO){
        return this.tagService.update(updateTagDTO, id);
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable UUID id){
        this.tagService.delete(id);

        return "Tag deleted with success";
    }

}
