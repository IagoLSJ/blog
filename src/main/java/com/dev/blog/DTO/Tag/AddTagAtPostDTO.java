package com.dev.blog.DTO.Tag;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Setter
@Getter
public class AddTagAtPostDTO {
    private UUID tagId;
    private UUID postId;

}
