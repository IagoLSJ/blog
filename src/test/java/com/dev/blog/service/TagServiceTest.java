package com.dev.blog.service;


import com.dev.blog.DTO.Tag.UpdateTagDTO;
import com.dev.blog.configs.ResourceNotFoundException;
import com.dev.blog.model.TagEntity;
import com.dev.blog.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {

    @InjectMocks
    TagService tagService;

    @Mock
    TagRepository tagRepository;

    TagEntity tag;
    UUID tagId = UUID.randomUUID();


    @BeforeEach
    public void setUp(){
        tag = new TagEntity();
        tag.setId(tagId);
        tag.setName("Moda");
    }

    @Test
    void findById_Success(){
        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));

        TagEntity foundTag = tagService.findById(tagId);

        assertEquals(tag, foundTag);

        verify(tagRepository).findById(tagId);

        verifyNoMoreInteractions(tagRepository);
    }

    @Test
    void findById_NotFound(){
        when(tagRepository.findById(tagId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->tagService.findById(tagId));

        verify(tagRepository).findById(tagId);

        verifyNoMoreInteractions(tagRepository);
    }

    @Test
    void findAll(){
        when(tagRepository.findAll()).thenReturn(List.of(tag));

        List<TagEntity> foundAllPosts = tagService.findAll();

        assertEquals(tag, foundAllPosts.get(0));

        verify(tagRepository).findAll();

        verifyNoMoreInteractions(tagRepository);
    }

    @Test
    void findByTitle_Success(){
        when(tagRepository.findByName("Moda")).thenReturn(List.of(tag));

        List<TagEntity> foundPostByTitle = tagService.findByName("Moda");

        assertEquals(tag, foundPostByTitle.get(0));

        verify(tagRepository).findByName("Moda");

        verifyNoMoreInteractions(tagRepository);
    }

    @Test
    void findByTitle_NotFound(){
        when(tagRepository.findByName("Morte")).thenReturn(new ArrayList<>());

        List<TagEntity> foundTagByTitle = tagService.findByName("Morte");

        assertTrue(foundTagByTitle.isEmpty());

        verify(tagRepository).findByName("Morte");

        verifyNoMoreInteractions(tagRepository);
    }

    @Test
    void updateTag_Success(){
        UpdateTagDTO updateTagDTO = new UpdateTagDTO();
        updateTagDTO.setName("New Name");

        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));
        when(tagRepository.save(any())).thenReturn(tag);

        TagEntity updatedTag = tagService.update(updateTagDTO, tagId);

        assertEquals(updateTagDTO.getName(), updatedTag.getName());
        verify(tagRepository).findById(tagId);
        verify(tagRepository).save(tag);
    }

    @Test
    void updateTag_TitleAlreadyInUse() {
        UpdateTagDTO updateTagDTO = new UpdateTagDTO();
        updateTagDTO.setName("Moda");

        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));

        assertThrows(ResourceNotFoundException.class, () -> tagService.update(updateTagDTO, tagId));

        verify(tagRepository).findById(tagId);
        verifyNoMoreInteractions(tagRepository);
    }

    @Test
    void deleteTag_Success(){
        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));

        tagService.delete(tagId);

        verify(tagRepository).findById(tagId);
        verify(tagRepository).delete(tag);
    }

}
