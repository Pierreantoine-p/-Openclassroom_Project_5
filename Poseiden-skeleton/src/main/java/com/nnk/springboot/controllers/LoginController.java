package com.nnk.springboot.controllers;

import com.nnk.springboot.service.LoginService;

import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("app")
public class LoginController {

	@Autowired
	private LoginService loginService;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	ModelAndView modelAndView = new ModelAndView();

	/**
	 * Get for login user
	 * @return mav
	 */
	@GetMapping("login")
	public ModelAndView login() {
		try {
			return loginService.login();

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			modelAndView.addObject("errorMessage", "Une erreur s'est produite. Veuillez contacter l'administrateur.");
			modelAndView.setViewName("error");
			return modelAndView;		
			}
	}

	/**
	 * Get for secure article
	 * @return mav
	 */
	@GetMapping("secure/article-details")
	public ModelAndView getAllUserArticles() {
		try {
			return loginService.getAllUserArticles();

		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			modelAndView.addObject("errorMessage", "Une erreur s'est produite. Veuillez contacter l'administrateur.");
			modelAndView.setViewName("error");
			return modelAndView;		
			}
	}

	/**
	 * Get error's page
	 * @return mav
	 */
	@GetMapping("error")
	public ModelAndView error() {
		return loginService.error();
	}

	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "/loginError"; 
	}

}