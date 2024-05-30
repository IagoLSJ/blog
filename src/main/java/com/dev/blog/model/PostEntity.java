package com.dev.blog.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "post")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Getter()
@Setter()
public class PostEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String content;

    private Date createdOn;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<PostCommentEntity> comments;

    @ManyToMany(mappedBy = "posts")
    @JsonManagedReference
    private List<TagEntity> tags;

}
