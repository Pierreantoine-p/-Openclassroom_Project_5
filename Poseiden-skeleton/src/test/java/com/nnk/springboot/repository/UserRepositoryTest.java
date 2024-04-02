package com.nnk.springboot.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;



@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class UserRepositoryTest {


	
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
		userRepository.save(user);
	}
	
	@AfterAll
	void cleanUp() {
		userRepository.deleteAll();
	}

	@Test
	@Order(1)
	public void testFindAll() {
		
		List<com.nnk.springboot.domain.User> userList = userRepository.findAll();
		User result = userList.get(0);
		assertEquals(result.getFullname(), user.getFullname());
	}
}
