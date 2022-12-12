package com.micro.userservice;

import com.micro.userservice.entities.Rating;
import com.micro.userservice.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	RatingService ratingService;

	@Test
	void createRating(){

		String expected = "201 CREATED";
		Rating rating = Rating.builder().rating(8).userId("").hotelId("").feedback("Good one").build();
		ResponseEntity<Rating> responseRating = ratingService.create(rating);

		HttpStatus resp = responseRating.getStatusCode();
		assertEquals(expected, resp.toString());
	}

}
