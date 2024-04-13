package com.nnk.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

import jakarta.validation.Valid;

@Service
public class BidListService {

	@Autowired
	private BidListRepository bidListRepository;

	private static final Logger logger = LoggerFactory.getLogger(BidListService.class);

	public String home(Model model) {
		try {
			model.addAttribute("bidList", bidListRepository.findAll());
			return "bidList/list";	
		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}

	}


	/**
	 * Post for validate bidList 
	 * @RequestBody BidList : bidList
	 * @return "redirect:/bidList/list"
	 */
	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {

		try {
			if(!result.hasErrors()) {
				bidListRepository.save(bid);
				model.addAttribute("bidList", bidListRepository.findAll());
				return "redirect:/bidList/list";
			}
			return "bidList/add";	

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}

	/**
	 * Update one bidList by id
	 * @Param id : id
	 * @return "bidList/update"
	 */
	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm( Integer id, Model model) {

		try {
			BidList bid = bidListRepository.findById(id).orElseThrow(() -> 
			new IllegalArgumentException("Invalid bidList Id:" + id));
			model.addAttribute("bidList", bid);
			return "bidList/update";	

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}

	/**
	 * Update one bidList by id
	 * @Param id : id
	 * @return "redirect:/bidList/list"
	 */
	@PostMapping("/bidList/update/{id}")
	public String updateBid(Integer id, @Valid BidList bidList,	BindingResult result, Model model) {

		try {

			if (result.hasErrors()) {
				return "bidList/update";
			}
			bidListRepository.save(bidList);
			model.addAttribute("bidList", bidListRepository.findAll());
			return "redirect:/bidList/list";

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}


	/**
	 * Delete one bidList by id
	 * @Param id : id
	 * @return "redirect:/bidList/list"
	 */
	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {

		try {
			BidList bid = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
			bidListRepository.delete(bid);
			model.addAttribute("bidList", bidListRepository.findAll());
			return "redirect:/bidList/list";

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}
}