package com.micro.userservice.config.services.impl;

import com.micro.userservice.config.services.UserService;
import com.micro.userservice.entities.Hotel;
import com.micro.userservice.entities.Rating;
import com.micro.userservice.entities.User;
import com.micro.userservice.exception.ResourceNotFound;
import com.micro.userservice.external.services.HotelService;
import com.micro.userservice.payload.ApiResponse;
import com.micro.userservice.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${rating.url}")
    private String ratingUrl;

    @Value("${hotel.url}")
    private String hotelUrl;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {

        logger.info("UserServiceImpl's saveUser method executing !");
        //generate unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        logger.info("UserServiceImpl's getAllUsers method executing !");
        List<User> users = userRepository.findAll();

        //TODO : implement getRating by userId
        for (User usr : users) {

            ArrayList<Rating> userRating = restTemplate.getForObject(ratingUrl+usr.getUserId(), ArrayList.class);
            usr.setRatings(userRating);
        }

        return users;
    }

    @Override
    public User getUserById(String userId) {

        logger.info("UserServiceImpl's getUserById method executing !");

        User user = userRepository.findById(userId).orElseThrow
                (()->new ResourceNotFound("User not found with id " +userId));

        // get Rating by userId
        Rating[] userRating = restTemplate.getForObject(ratingUrl + user.getUserId(), Rating[].class);

        List<Rating> ratings = Arrays.stream(userRating).collect(Collectors.toList());

        //getHotels by hotelId for every users rating
        List<Rating> ratingList = ratings.stream().map(rating-> {

            //  ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity(hotelUrl + rating.getHotelId(), Hotel.class);
            //  Hotel hotel=hotelEntity.getBody();
            //  logger.info("response status code : " + hotelEntity.getStatusCode());

            //  used Feign client
            Hotel hotel = hotelService.getHotel(rating.getHotelId());

            rating.setHotel(hotel);

            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }

    @Override
    public ResponseEntity<ApiResponse> deleteUserById(String userId) {

        logger.info("UserServiceImpl's deleteUserById method executing !");

        User usr = userRepository.findById(userId).orElseThrow
                (()->new ResourceNotFound("User not found with id " +userId));

        userRepository.deleteById(usr.getUserId());
        ApiResponse response = ApiResponse.builder().message("Successfully deleted UserId " +userId)
                .success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }
}
