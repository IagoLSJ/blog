package com.dev.blog.service;

import com.dev.blog.DTO.userVote.CreateUserVoteDTO;
import com.dev.blog.model.UserVoteEntity;
import com.dev.blog.repository.PostCommentRepository;
import com.dev.blog.repository.UserRepository;
import com.dev.blog.repository.UserVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
public class UserVoteService {
    @Autowired
    private UserVoteRepository userVoteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostCommentRepository postCommentRepository;

    public UserVoteEntity like(CreateUserVoteDTO createUserVoteDTO){
        var commentById = this.postCommentRepository.findById(createUserVoteDTO.getCommentId());
        var userById = this.userRepository.findById(createUserVoteDTO.getUserId());

        if(!commentById.isPresent()){
            throw new NoSuchElementException("Comment not exist");
        }

        if (!userById.isPresent()){
            throw new NoSuchElementException("User not exist");
        }

        UserVoteEntity vote = new UserVoteEntity();

        vote.setUser(userById.get());
        vote.setComment(commentById.get());
        vote.setScore(1);

        return this.userVoteRepository.save(vote);
    }

}
