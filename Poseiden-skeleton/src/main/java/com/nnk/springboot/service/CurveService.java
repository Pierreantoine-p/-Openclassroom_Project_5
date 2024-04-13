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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurveRepository;

import jakarta.validation.Valid;

@Service
public class CurveService {

	@Autowired
	private CurveRepository curvePointRepository;

	private static final Logger logger = LoggerFactory.getLogger(CurveService.class);

	
	@RequestMapping("/curvePoint/list")
	public String home(Model model)
	{

		try {
			model.addAttribute("curvePoint", curvePointRepository.findAll());
			return "curvePoint/list";

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}


	/**
	 * Post for validate curvePoint 
	 * @RequestBody CurvePoint : curvePoint
	 * @return "redirect:/curvePoint/list"
	 */
	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

		try {
			if (!result.hasErrors()) {
				curvePointRepository.save(curvePoint);
				model.addAttribute("curvePoint", curvePointRepository.findAll());
				return "redirect:/curvePoint/list";

			}
			return "curvePoint/add";

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}

	/**
	 * Update one curvePoint by id
	 * @Param id : id
	 * @return "curvePoint/update"
	 */
	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		try {
			CurvePoint curvePoint  = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
			model.addAttribute("curvePoint", curvePoint);
			return "curvePoint/update";

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}

	/**
	 * Update one curvePoint by id
	 * @Param id : id
	 * @return "redirect:/curvePoint/list"
	 */
	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
			BindingResult result, Model model) {

		try {
			if (result.hasErrors()) {
				return "curvePoint/update";
			}
			curvePointRepository.save(curvePoint);
			model.addAttribute("curvePoint", curvePointRepository.findAll());
			return "redirect:/curvePoint/list";

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}

	/**
	 * Delete one curvePoint by id
	 * @Param id : id
	 * @return "redirect:/curvePoint/list"
	 */
	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {

		try {

			CurvePoint curvePoint  = curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
			curvePointRepository.delete(curvePoint);
			model.addAttribute("curvePoint", curvePointRepository.findAll());
			return "redirect:/curvePoint/list";
		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}
}
