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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

import jakarta.validation.Valid;

@Service
public class TradeService {

	@Autowired
	private TradeRepository tradeRepository;

	private static final Logger logger = LoggerFactory.getLogger(TradeService.class);


	@RequestMapping("/trade/list")
	public String home(Model model)
	{


		try {
			model.addAttribute("trades", tradeRepository.findAll());
			return "trade/list";

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}



	/**
	 * Post for validate trade 
	 * @RequestBody Trade : trade
	 * @return "redirect:/trade/list"
	 * @return "redirect:/trade/list"
	 */
	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {

		try {
			if (!result.hasErrors()) {
				tradeRepository.save(trade);
				model.addAttribute("trades", tradeRepository.findAll());
				return "redirect:/trade/list";
			}
			return "trade/add";

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}

	/**
	 * Update one trade by id
	 * @Param id : id
	 * @return "trade/update"
	 */
	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		try {
			Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
			model.addAttribute("trade", trade);
			return "trade/update";

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}

	/**
	 * Update one trade by id
	 * @Param id : id
	 * @return "redirect:/trade/list"
	 * @return"trade/update"
	 */
	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {

		try {

			if (result.hasErrors()) {
				return "trade/update";
			}
			tradeRepository.save(trade);
			model.addAttribute("trades", tradeRepository.findAll());
			return "redirect:/trade/list";
		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}

	/**
	 * Delete one trade by id
	 * @Param id : id
	 * @return "redirect:/trade/list"
	 */
	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {

		try {
			Trade trade = tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
			tradeRepository.delete(trade);
			model.addAttribute("trades", tradeRepository.findAll());
			return "redirect:/trade/list";

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}

}
