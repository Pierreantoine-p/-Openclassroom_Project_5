package com.nnk.springboot.controller;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.nnk.springboot.controllers.HomeController;

import static org.junit.jupiter.api.Assertions.assertEquals;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class HomeControllerTest {
	
	@Autowired
	private HomeController homeController;
	
	@Test
	@Order(1)
	public void testHome() {
		String result = homeController.home(null);
		
		assertEquals("home", result );

	}
	
	@Test
	@Order(2)
	public void testAdminHome() {
		String result = homeController.adminHome(null);
		
		assertEquals("redirect:/bidList/list", result );

	}
}
