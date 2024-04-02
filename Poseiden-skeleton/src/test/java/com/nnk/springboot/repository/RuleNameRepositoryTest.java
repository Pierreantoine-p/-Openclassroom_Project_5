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
import org.springframework.test.context.ActiveProfiles;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class RuleNameRepositoryTest {


	@Autowired
	private RuleNameRepository ruleNameRepository;

	private RuleName ruleName = new RuleName();
	

	@BeforeAll
	void createRuleName() {
		ruleName.setName("a");
		ruleName.setDescription("a");
		ruleName.setJson("a");
		ruleName.setTemplate("a");
		ruleName.setSqlStr("a");
		ruleName.setSqlPart("a");
		ruleNameRepository.save(ruleName);
	}
	
	@AfterAll
	void cleanUp() {
		ruleNameRepository.deleteAll();
	}
	
	@Test
	@Order(1)
	public void testFindAll() {
		
		List<RuleName> ruleNameList = ruleNameRepository.findAll();
		RuleName result = ruleNameList.get(0);
		assertEquals(result.getId(), ruleName.getId());
	}
}
