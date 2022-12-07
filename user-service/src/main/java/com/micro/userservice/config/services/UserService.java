package com.micro.userservice.config.services;

import com.micro.userservice.entities.User;
import com.micro.userservice.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    //create User
    User saveUser(User user);

    //getAll Users
    List<User> getAllUsers();

    //get user by id
    User getUserById(String userId);

    ResponseEntity<ApiResponse> deleteUserById(String userId);
}
