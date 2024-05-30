package com.dev.blog.DTO.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateUserDTO {
    private String name;
    private String email;
}
