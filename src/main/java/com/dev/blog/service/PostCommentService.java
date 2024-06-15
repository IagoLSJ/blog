package com.dev.blog.service;

import com.dev.blog.DTO.postComment.CreatePostCommentDTO;
import com.dev.blog.DTO.postComment.ReturnPostCommentDTO;
import com.dev.blog.configs.ResourceNotFoundException;
import com.dev.blog.model.PostCommentEntity;
import com.dev.blog.repository.PostCommentRepository;
import com.dev.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostCommentService {

    @Autowired
    private PostCommentRepository postCommentRepository;

    @Autowired
    private PostRepository postRepository;

    public ReturnPostCommentDTO create(CreatePostCommentDTO createPostCommentDTO){
        var postById = this.postRepository.findById(createPostCommentDTO.getPostId());

        if(!postById.isPresent()){
            throw new ResourceNotFoundException("Post not exist");
        }

        PostCommentEntity comment = new PostCommentEntity();

        comment.setPost(postById.get());
        comment.setReview(createPostCommentDTO.getReview());

        var result =  this.postCommentRepository.save(comment);

        ReturnPostCommentDTO resultWithDTO = new ReturnPostCommentDTO();
        resultWithDTO.setId(result.getId());
        resultWithDTO.setPost(result.getPost());
        resultWithDTO.setReview(result.getReview());
        resultWithDTO.setVotes(result.getVotes());
        return resultWithDTO;

    }


}
