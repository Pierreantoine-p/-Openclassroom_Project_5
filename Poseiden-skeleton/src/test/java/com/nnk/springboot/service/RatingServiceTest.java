package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class RatingServiceTest {
	
	@Autowired
	private RatingController ratingController;
	
	@Autowired
	private RatingService ratingService;

	@Autowired
	private RatingRepository ratingRepository;

	private Rating rating = new Rating();

	@BeforeAll
	void createRating() {
		rating.setMoodysRating("a");
		rating.setSandPRating("a");
		rating.setFitchRating("a");
		rating.setOrderNumber(1);
	}
	
	@AfterAll
	void cleanUp() {
		ratingRepository.deleteAll();
	}

	@Test
	@Order(1)
	public void testHome() {

		Model model = mock(Model.class);

		String result = ratingService.home(model);
		assertEquals("rating/list", result );

	}

	@Test
	@Order(3)
	public void testValidate() {


		BindingResult bindingResult = mock(BindingResult.class);
		Model model = mock(Model.class);

		String result = ratingService.validate(rating, bindingResult, model);

		assertEquals("redirect:/rating/list", result );

	}

	@Test
	@Order(4)
	public void testShowUpdateForm() {

		Model model = mock(Model.class);
		String result = ratingService.showUpdateForm(rating.getId() , model);

		assertEquals("rating/update", result );

	}

	@Test
	@Order(5)
	public void testUpdateBid() {
		Rating newRating = new Rating();

		newRating.setMoodysRating("b");
		newRating.setSandPRating("b");
		newRating.setFitchRating("b");
		newRating.setOrderNumber(2);

		Model model = mock(Model.class);
		BindingResult bindingResult = mock(BindingResult.class);

		String result = ratingService.updateRating(rating.getId(), newRating, bindingResult, model);

		assertEquals("redirect:/rating/list", result );

	}

	@Test
	@Order(6)
	public void testDeleteBid() {
		Model model = mock(Model.class);
		String result = ratingService.deleteRating(rating.getId(), model);
		assertEquals("redirect:/rating/list", result );

	}

}
