package com.dev.blog.service;

import com.dev.blog.model.UserEntity;
import com.dev.blog.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> findAll(){
        return this.userRepository.findAll();
    }

    public UserEntity create(String name, String email){
        UserEntity newUser = new UserEntity();
        newUser.setFristName(name);
        newUser.setEmail(email);
        return this.userRepository.save(newUser);
   }



}
