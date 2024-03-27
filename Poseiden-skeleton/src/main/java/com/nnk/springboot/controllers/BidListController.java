package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;


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
public class BidListController {

	@Autowired
	private BidListRepository bidListRepository;

	@Autowired
	private BidListService bidListService;

	@RequestMapping("/bidList/list")
	public String home(Model model)
	{
		return bidListService.home(model);
	}

	/**
	 * Get all bidList
	 * @return "bidList/add"
	 */
	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		return bidListService.addBidForm(bid);
	}

	/**
	 * Post for validate bidList 
	 * @RequestBody BidList : bidList
	 * @return "redirect:/bidList/list"
	 */
	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		return bidListService.validate(bid,result,model);
	}

	/**
	 * Update one bidList by id
	 * @Param id : id
	 * @return "bidList/update"
	 */
	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		return bidListService.showUpdateForm(id,model);

	}

	/**
	 * Update one bidList by id
	 * @Param id : id
	 * @return "redirect:/bidList/list"
	 */
	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,BindingResult result, Model model) {
			return bidListService.updateBid(id,bidList, result, model);

	}

	/**
	 * Delete one bidList by id
	 * @Param id : id
	 * @return "redirect:/bidList/list"
	 */
	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		return bidListService.deleteBid(id, model);
	}
}
