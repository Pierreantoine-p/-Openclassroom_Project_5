package com.nnk.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.controllers.LoginController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

import jakarta.validation.Valid;

@Service
public class RatingService {
	@Autowired
	private RatingRepository ratingRepository;

	private static final Logger logger = LoggerFactory.getLogger(RatingService.class);

	@RequestMapping("/rating/list")
	public String home(Model model)
	{

		try {

			model.addAttribute("rating", ratingRepository.findAll());
			return "rating/list";
		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}


	/**
	 * Post for validate user rating
	 * @RequestBody Rating : rating
	 * @return "redirect:/rating/list"
	 */
	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {

		try {
			if (!result.hasErrors()) {
				ratingRepository.save(rating);
				model.addAttribute("ratings", ratingRepository.findAll());
				return "redirect:/rating/list";

			}
			return "rating/add";

		}catch( Exception  e) {
			logger.error("error :" + e);
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
			Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
			model.addAttribute("rating", rating);
			return "rating/update";

		}catch( Exception  e) {
			logger.error("error :" + e);
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

			if (result.hasErrors()) {
				return "rating/update";
			}
			ratingRepository.save(rating);
			model.addAttribute("ratings", ratingRepository.findAll());
			return "redirect:/rating/list";
		}catch( Exception  e) {
			logger.error("error :" + e);
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
			Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
			ratingRepository.delete(rating);
			model.addAttribute("ratings", ratingRepository.findAll());
			return "redirect:/rating/list";

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}
}
