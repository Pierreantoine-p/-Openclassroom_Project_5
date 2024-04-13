package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;

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
public class RuleNameController {
	@Autowired
	private RuleNameService ruleNameService;

	private static final Logger logger = LoggerFactory.getLogger(RuleNameController.class);

	@RequestMapping("/ruleName/list")
	public String home(Model model)
	{
		try {
			return ruleNameService.home(model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}

	/**
	 * Get all ruleNames
	 * @return "ruleName/add"
	 */
	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName bid) {
		return "ruleName/add";
	}

	/**
	 * Post for validate user ruleName
	 * @RequestBody RuleName : ruleName
	 * @return "redirect:/ruleName/list"
	 */
	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
		try {
			return ruleNameService.validate(ruleName, result, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
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
			return ruleNameService.showUpdateForm(id, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
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
			return ruleNameService.updateRuleName(id, ruleName, result, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
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
			return ruleNameService.deleteRuleName(id, model);

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			return "error";
		}
	}
}
