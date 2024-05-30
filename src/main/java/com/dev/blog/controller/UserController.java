package com.dev.blog.controller;

import com.dev.blog.DTO.user.CreateUserDTO;
import com.dev.blog.model.UserEntity;
import com.dev.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/")
    public List<UserEntity> findAll(){
        return this.userService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<UserEntity> createUser(@RequestBody CreateUserDTO createUserDTO) {
       UserEntity newUser = this.userService.create(createUserDTO.getName(), createUserDTO.getEmail());
        return ResponseEntity.ok(newUser);
    }

}
