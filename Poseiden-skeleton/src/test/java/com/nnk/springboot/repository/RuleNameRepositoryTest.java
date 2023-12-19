package com.nnk.springboot.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class RuleNameRepositoryTest {
	
	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Test
	@Order(1)
	public void testFindAll() {
		
		List<RuleName> ruleName = ruleNameRepository.findAll();
		
		assertTrue(ruleName.size()> 0);
	}
}
