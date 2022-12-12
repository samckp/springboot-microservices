package com.micro.userservice.external.services;

import com.micro.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @PostMapping("/rating")
    public ResponseEntity<Rating> create(Rating values);

    @PutMapping("/rating/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratingId, Rating rating );

    @DeleteMapping("/rating/{ratingId}")
    public void deleteRating(@PathVariable("ratingId") String ratingId);
}
