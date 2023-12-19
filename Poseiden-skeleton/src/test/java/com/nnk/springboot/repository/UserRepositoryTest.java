package com.nnk.springboot.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.repositories.UserRepository;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	@Order(1)
	public void testFindAll() {
		
		List<com.nnk.springboot.domain.User> user = userRepository.findAll();
		
		assertTrue(user.size()> 0);
	}
}
