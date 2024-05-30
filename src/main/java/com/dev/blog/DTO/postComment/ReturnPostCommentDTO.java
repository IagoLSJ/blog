package com.dev.blog.DTO.postComment;

import com.dev.blog.DTO.post.ReturnPostDTO;
import com.dev.blog.model.PostEntity;
import com.dev.blog.model.UserVoteEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
@Setter
@Getter
public class ReturnPostCommentDTO {
    private UUID id;
    private PostEntity post;
    private String review;
    private List<UserVoteEntity> votes;


}
