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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class RuleNameServiceTest {
	
	@Autowired
	private RuleNameController ruleNameController;

	@Autowired
	private RuleNameService ruleNameService;

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
	}
	
	@AfterAll
	void cleanUp() {
		ruleNameRepository.deleteAll();
	}
	

	@Test
	@Order(1)
	public void testHome() {

		Model model = mock(Model.class);

		String result = ruleNameService.home(model);
		assertEquals("ruleName/list", result );

	}

	@Test
	@Order(3)
	public void testValidate() {


		BindingResult bindingResult = mock(BindingResult.class);
		Model model = mock(Model.class);

		String result = ruleNameService.validate(ruleName, bindingResult, model);

		assertEquals("redirect:/ruleName/list", result );

	}

	@Test
	@Order(4)
	public void testShowUpdateForm() {

		Model model = mock(Model.class);
		String result = ruleNameService.showUpdateForm(ruleName.getId() , model);

		assertEquals("ruleName/update", result );

	}

	@Test
	@Order(5)
	public void testUpdateBid() {
		 RuleName NewRuleName = new RuleName();

			ruleName.setName("b");
			ruleName.setDescription("b");
			ruleName.setJson("b");
			ruleName.setTemplate("b");
			ruleName.setSqlStr("b");
			ruleName.setSqlPart("b");

		Model model = mock(Model.class);
		BindingResult bindingResult = mock(BindingResult.class);

		String result = ruleNameService.updateRuleName(ruleName.getId(), NewRuleName, bindingResult, model);

		assertEquals("redirect:/ruleName/list", result );

	}

	@Test
	@Order(6)
	public void testDeleteBid() {
		Model model = mock(Model.class);
		String result = ruleNameService.deleteRuleName(ruleName.getId(), model);
		assertEquals("redirect:/ruleName/list", result );

	}
	
}
