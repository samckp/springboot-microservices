package com.micro.userservice.services.impl;

import com.micro.userservice.entities.Rating;
import com.micro.userservice.entities.User;
import com.micro.userservice.exception.ResourceNotFound;
import com.micro.userservice.payload.ApiResponse;
import com.micro.userservice.repositories.UserRepository;
import com.micro.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${rating.url:Rating url not found!!}")
    String ratingUrl;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

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

        //TODO : implement getRating by userId
        
    }

    @Override
    public User getUserById(String userId) {

        User user = userRepository.findById(userId).orElseThrow
                (()->new ResourceNotFound("User not found with id " +userId));

        // get Rating by userId
        ArrayList<Rating> userRating = restTemplate.getForObject(ratingUrl+user.getUserId(), ArrayList.class);
        user.setRatings(userRating);
        return user;
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
