package com.nnk.springboot.controller;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private UserController userController;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
	@Order(1)
	public void testAddUsers() {		
		 BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
		 
		User user = new User();
		user.setUsername("marty");
		user.setFullname("mcfly");
		user.setPassword(crypt.encode("88miles"));
		user.setRole("ADMIN");
		
		userController.addUser(user);
		
		User userExpected = userRepository.findByUsername(user.getUsername());
		assertEquals("marty", userExpected.getUsername());
	}
}
