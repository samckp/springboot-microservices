package com.micro.userservice.external.services;

import com.micro.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @PostMapping("/rating")
    public ResponseEntity<Rating> create(Rating values);

    @PutMapping("/rating/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratingId, Rating rating );

    @GetMapping("/rating/user/{userId}")
    public List<Rating> getRatingByUserId(@PathVariable String userId);

}
