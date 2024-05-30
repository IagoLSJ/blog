package com.dev.blog.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity(name = "postComment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter()
@Setter()
public class PostCommentEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private PostEntity post;
    private String review;

    @OneToMany(mappedBy = "comment")
    @JsonManagedReference
    private List<UserVoteEntity> votes;


}
