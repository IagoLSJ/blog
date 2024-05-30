package com.dev.blog.DTO.post;

import com.dev.blog.model.PostCommentEntity;
import com.dev.blog.model.TagEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Setter
@Getter
public class ReturnPostDTO {
    private UUID id;
    private String title;
    private String content;
    private Date createdOn;
    private List<PostCommentEntity> comments;
    private List<TagEntity> tags;
}
