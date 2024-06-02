package com.dev.blog.controller;

import com.dev.blog.DTO.user.CreateUserDTO;
import com.dev.blog.model.UserEntity;
import com.dev.blog.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserEntity> createUser(@RequestBody CreateUserDTO createUserDTO) {
       UserEntity newUser = this.userService.create(createUserDTO);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable UUID id){
        UserEntity userById = this.userService.findById(id);

        return ResponseEntity.ok(userById);
    }

    @GetMapping("/by-username/{name}")
    public ResponseEntity<List<UserEntity>> findById(@PathVariable String name){
        List<UserEntity> userById = this.userService.findUsersByUsername(name);

        return ResponseEntity.ok(userById);
    }


}
