package com.nnk.springboot.controllers;

import com.nnk.springboot.service.LoginService;
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

    /**
   	 * Get for login user
   	 * @return mav
   	 */
    @GetMapping("login")
    public ModelAndView login() {
 
        return loginService.login();
    }

    /**
  	 * Get for secure article
  	 * @return mav
  	 */
    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {

        return loginService.getAllUserArticles();
    }

    /**
  	 * Get error's page
  	 * @return mav
  	 */
    @GetMapping("error")
    public ModelAndView error() {
        return loginService.error();
    }
    

}