package com.nnk.springboot.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class RatingRepositoryTest {
	

	@Autowired
	private RatingRepository ratingRepository;

	private Rating rating = new Rating();

	@BeforeAll
	void createRating() {
		rating.setMoodysRating("a");
		rating.setSandPRating("a");
		rating.setFitchRating("a");
		rating.setOrderNumber(1);
		
		ratingRepository.save(rating);
	}
	
	@AfterAll
	void cleanUp() {
		ratingRepository.deleteAll();
	}

	
	@Test
	@Order(1)
	public void testFindAll() {
		
		List<Rating> ratingList = ratingRepository.findAll();
		Rating result = ratingList.get(0);
		assertEquals(result.getMoodysRating(), rating.getMoodysRating());
	}
}
