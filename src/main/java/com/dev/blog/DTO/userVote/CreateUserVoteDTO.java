package com.dev.blog.DTO.userVote;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class CreateUserVoteDTO {
    private UUID userId;
    private UUID commentId;
}
