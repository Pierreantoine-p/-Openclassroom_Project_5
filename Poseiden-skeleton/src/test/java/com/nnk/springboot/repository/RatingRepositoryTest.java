package com.nnk.springboot.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class RatingRepositoryTest {
	
	@Autowired
	private RatingRepository ratingRepository;
	
	@Test
	@Order(1)
	public void testFindAll() {
		
		List<Rating> rating = ratingRepository.findAll();
		
		assertTrue(rating.size()> 0);
	}
}
