package com.dev.blog.controller;

import com.dev.blog.DTO.userVote.CreateUserVoteDTO;
import com.dev.blog.model.UserVoteEntity;
import com.dev.blog.service.UserVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-vote")
public class UserVoteController {

    @Autowired
    private UserVoteService userVoteService;

    @PostMapping("/")
    public UserVoteEntity create(@RequestBody CreateUserVoteDTO createUserVoteDTO){
        return this.userVoteService.like(createUserVoteDTO);
    }
}
