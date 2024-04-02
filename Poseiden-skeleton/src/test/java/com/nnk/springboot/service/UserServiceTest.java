package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class UserServiceTest {
	
	@Autowired
    private UserController userController;
    
	@Autowired
	private UserService userService;
	
    @Autowired
    private UserRepository userRepository;
    
	private User user = new User();
	
	private BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();

	@BeforeAll
	void createbid() {
		user.setUsername("marty");
		user.setFullname("mcfly");
		user.setPassword(crypt.encode("88miles"));
		user.setRole("ADMIN");
	}
	
	@AfterAll
	void cleanUp() {
		userRepository.deleteAll();
	}
	

	@Test
	@Order(1)
	public void testHome() {

		Model model = mock(Model.class);

		String result = userService.home(model);
		assertEquals("user/list", result );

	}

	@Test
	@Order(3)
	public void testValidate() {


		BindingResult bindingResult = mock(BindingResult.class);
		Model model = mock(Model.class);

		String result = userService.validate(user, bindingResult, model);

		assertEquals("redirect:/user/list", result );

	}

	@Test
	@Order(4)
	public void testShowUpdateForm() {

		Model model = mock(Model.class);
		String result = userService.showUpdateForm(user.getId() , model);

		assertEquals("user/update", result );

	}

	@Test
	@Order(5)
	public void testUpdateUser() {
		User newUser = new User();

		newUser.setUsername("b");
		newUser.setFullname("b");
		newUser.setPassword(crypt.encode("b"));
		newUser.setRole("ADMIN");

		Model model = mock(Model.class);
		BindingResult bindingResult = mock(BindingResult.class);

		String result = userService.updateUser(user.getId(), newUser, bindingResult, model);

		assertEquals("redirect:/user/list", result );

	}

	@Test
	@Order(6)
	public void testDeleteUser() {
		Model model = mock(Model.class);
		String result = userService.deleteUser(user.getId(), model);
		assertEquals("redirect:/user/list", result );

	}
}
