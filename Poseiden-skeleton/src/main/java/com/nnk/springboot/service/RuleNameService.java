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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

import jakarta.validation.Valid;

@Service
public class RuleNameService {

	@Autowired
	private RuleNameRepository ruleNameRepository;
	private static final Logger logger = LoggerFactory.getLogger(RuleNameService.class);

	@RequestMapping("/ruleName/list")
	public String home(Model model)
	{

		try {
			model.addAttribute("ruleNames", ruleNameRepository.findAll());
			return "ruleName/list";	

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}



	/**
	 * Post for validate user ruleName
	 * @RequestBody RuleName : ruleName
	 * @return "redirect:/ruleName/list"
	 */
	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {

		try {

			if (!result.hasErrors()) {
				ruleNameRepository.save(ruleName);
				model.addAttribute("ruleNames", ruleNameRepository.findAll());
				return "redirect:/ruleName/list";
			}
			return "ruleName/add";
		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}

	/**
	 * Update one ruleName by id
	 * @Param id : id
	 * @return "ruleName/update"
	 */
	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		try {

			RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
			model.addAttribute("ruleName", ruleName);
			return "ruleName/update";
		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}

	/**
	 * Update one ruleName by id
	 * @Param id : id
	 * @return "redirect:/ruleName/list"
	 */
	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
			BindingResult result, Model model) {

		try {

			if (result.hasErrors()) {
				return "ruleName/update";
			}
			ruleNameRepository.save(ruleName);
			model.addAttribute("ruleName", ruleNameRepository.findAll());
			return "redirect:/ruleName/list";
		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}

	/**
	 * Delete one ruleName by id
	 * @Param id : id
	 * @return "redirect:/ruleName/list"
	 */
	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {

		try {
			RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
			ruleNameRepository.delete(ruleName);

			model.addAttribute("ruleName", ruleNameRepository.findAll());
			return "redirect:/ruleName/list";

		}catch( Exception  e) {
			logger.error("error :" + e);
			return "error";
		}
	}

}
