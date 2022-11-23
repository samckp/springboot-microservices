package com.micro.userservice.services;

import com.micro.userservice.entities.User;

import java.util.List;

public interface UserService {

    //create User
    User saveUser(User user);

    //getAll Users
    List<User> getAllUsers();

    //get user by id
    User getUserById(String userId);


}
