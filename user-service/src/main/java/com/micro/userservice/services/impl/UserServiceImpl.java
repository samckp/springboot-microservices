package com.micro.userservice.services.impl;

import com.micro.userservice.entities.User;
import com.micro.userservice.exception.ResourceNotFound;
import com.micro.userservice.repositories.UserRepository;
import com.micro.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow
                (()->new ResourceNotFound("User not found with id " +userId));
    }
}
