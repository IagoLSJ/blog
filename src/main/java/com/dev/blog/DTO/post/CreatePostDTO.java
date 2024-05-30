package com.dev.blog.DTO.post;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
public class CreatePostDTO {
    private String title;
    private String content;
}
