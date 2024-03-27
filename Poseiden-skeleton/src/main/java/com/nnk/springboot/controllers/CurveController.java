package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.CurvePointService;

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
public class CurveController {

	@Autowired
	private CurvePointService curvePointService;

	@RequestMapping("/curvePoint/list")
	public String home(Model model)
	{
		return curvePointService.home(model);
	}

	  /**
  	 * Get all curvePoint
  	 * @return "curvePoint/add"
  	 */
	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid) {
		return curvePointService.addBidForm(bid);
	}

	  /**
  	 * Post for validate curvePoint 
  	 * @RequestBody CurvePoint : curvePoint
  	 * @return "redirect:/curvePoint/list"
  	 */
	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		
		return curvePointService.validate(curvePoint, result, model);
	}

	   /**
	  	 * Update one curvePoint by id
	  	 * @Param id : id
	  	 * @return "curvePoint/update"
	  	 */
	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
	
		return curvePointService.showUpdateForm(id, model);
	}

	 /**
  	 * Update one curvePoint by id
  	 * @Param id : id
  	 * @return "redirect:/curvePoint/list"
  	 */
	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,BindingResult result, Model model) {

		return curvePointService.updateBid(id, curvePoint, result, model);
	}

	/**
	 * Delete one curvePoint by id
	 * @Param id : id
	 * @return "redirect:/curvePoint/list"
	 */
	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {

		return curvePointService.deleteBid(id, model);
	}
}
