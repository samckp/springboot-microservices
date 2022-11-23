package com.micro.userservice.services.impl;

import com.micro.userservice.entities.User;
import com.micro.userservice.exception.ResourceNotFound;
import com.micro.userservice.payload.ApiResponse;
import com.micro.userservice.repositories.UserRepository;
import com.micro.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {

        //generate unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
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

    @Override
    public ResponseEntity<ApiResponse> deleteUserById(String userId) {

        User usr = userRepository.findById(userId).orElseThrow
                (()->new ResourceNotFound("User not found with id " +userId));

        userRepository.deleteById(usr.getUserId());
        ApiResponse response = ApiResponse.builder().message("Successfully deleted UserId " +userId)
                .success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }
}
