package com.micro.userservice.controller;

import com.micro.userservice.entities.User;
import com.micro.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(User user){

        User usr = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).
    }
}
