package com.dev.blog.DTO.postComment;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class CreatePostCommentDTO {
    private UUID postId;
    private String review;
}
