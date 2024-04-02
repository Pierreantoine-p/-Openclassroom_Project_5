package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.TradeService;

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
public class TradeController {

	@Autowired
	private TradeService tradeService;


	@RequestMapping("/trade/list")
	public String home(Model model)
	{
		return tradeService.home(model);
	}

	/**
	 * Get all trades
	 * @return "trade/add"
	 */
	@GetMapping("/trade/add")
	public String addUser(Trade bid) {
		return "trade/add";
	}

	/**
	 * Post for validate trade 
	 * @RequestBody Trade : trade
	 * @return "redirect:/trade/list"
	 * @return "redirect:/trade/list"
	 */
	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {

		return tradeService.validate(trade, result, model);
	}

	/**
	 * Update one trade by id
	 * @Param id : id
	 * @return "trade/update"
	 */
	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		return tradeService.showUpdateForm(id, model);
	}

	/**
	 * Update one trade by id
	 * @Param id : id
	 * @return "redirect:/trade/list"
	 * @return"trade/update"
	 */
	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
			BindingResult result, Model model) {

		return tradeService.updateTrade(id, trade, result, model);
	}

	/**
	 * Delete one trade by id
	 * @Param id : id
	 * @return "redirect:/trade/list"
	 */
	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {
		return tradeService.deleteTrade(id, model);
	}
}
