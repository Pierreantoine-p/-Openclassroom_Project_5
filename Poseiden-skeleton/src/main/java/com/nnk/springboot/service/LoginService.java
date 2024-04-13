package com.nnk.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.controllers.LoginController;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class LoginService {

	@Autowired
    private UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	ModelAndView modelAndView = new ModelAndView();

	
	  /**
   	 * Get for login user
   	 * @return mav
   	 */
    @GetMapping("login")
    public ModelAndView login() {
       
	try {
			 ModelAndView mav = new ModelAndView();
        mav.setViewName("/bidList/list");
        return mav;
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
		ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;	
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
       
	try {
			 ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
		}catch (Exception ex) {
			logger.error("Une erreur s'est produite : ", ex);
			modelAndView.addObject("errorMessage", "Une erreur s'est produite. Veuillez contacter l'administrateur.");
			modelAndView.setViewName("error");
			return modelAndView;		
			}
    }
}
