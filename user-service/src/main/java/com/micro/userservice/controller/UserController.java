package com.micro.userservice.controller;

import com.micro.userservice.entities.User;
import com.micro.userservice.payload.ApiResponse;
import com.micro.userservice.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){

        User usr = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(usr);
    }
    @GetMapping("/{userId}")
    @CircuitBreaker(name="hotelRatingBreaker", fallbackMethod = "hotelRatingFallback")
    public ResponseEntity<User> getUserById(@PathVariable String userId){

        User usr = userService.getUserById(userId);
        return ResponseEntity.ok(usr);
    }

    public ResponseEntity<User> hotelRatingFallback(String userId, Exception ex){

        logger.info("Fallback is executed, service is down !!");
        User user = User.builder().userId("dummyId")
                        .email("dummy@email.com")
                        .about("dummy about")
                        .build();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteByUserId(@PathVariable String userId){

        return userService.deleteUserById(userId);
    }
}
