package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
public class RatingController {

	@Autowired
	private RatingService ratingService;

	private static final Logger logger = LoggerFactory.getLogger(RatingController.class);

	@RequestMapping("/rating/list")
	public String home(Model model){
		try {
			return ratingService.home(model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}

	/**
	 * Get all rating
	 * @return "rating/add"
	 */
	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}

	/**
	 * Post for validate user rating
	 * @RequestBody Rating : rating
	 * @return "redirect:/rating/list"
	 */
	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		try {
			return ratingService.validate(rating, result, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}

	/**
	 * Update one rating by id
	 * @Param id : id
	 * @return "rating/update"
	 */
	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		try {
			return ratingService.showUpdateForm(id, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}

	/**
	 * Update one rating by id
	 * @Param id : id
	 * @return "redirect:/rating/list"
	 */
	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
			BindingResult result, Model model) {
		try {
			return ratingService.updateRating(id, rating, result, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}

	/**
	 * Delete one rating by id
	 * @Param id : id
	 * @return "redirect:/rating/list"
	 */
	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		try {
			return ratingService.deleteRating(id, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}
}
