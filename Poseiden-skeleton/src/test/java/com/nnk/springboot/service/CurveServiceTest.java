package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.time.Instant;

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

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurveRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class CurveServiceTest {



	@Autowired
	private CurveService curveService;

	@Autowired
	private CurveRepository curveRepository;

	CurvePoint curvePoint = new CurvePoint();

	@BeforeAll
	void createCurve() {
		Instant instant = Instant.now();
		Timestamp timestamp = Timestamp.from(instant);

		curvePoint.setCurveId(1);
		curvePoint.setAsOfDate(timestamp);
		curvePoint.setTerm(6.0);
		curvePoint.setValue(6.0);
		curvePoint.setCreationDate(timestamp);
	}

	@AfterAll
	void cleanUp() {
		curveRepository.deleteAll();
	}
	

	@Test
	@Order(1)
	public void testHome() {

		Model model = mock(Model.class);

		String result = curveService.home(model);
		assertEquals("curvePoint/list", result );

	}

	@Test
	@Order(3)
	public void testValidate() {


		BindingResult bindingResult = mock(BindingResult.class);
		Model model = mock(Model.class);

		String result = curveService.validate(curvePoint, bindingResult, model);

		assertEquals("redirect:/curvePoint/list", result );

	}

	@Test
	@Order(4)
	public void testShowUpdateForm() {

		Model model = mock(Model.class);
		String result = curveService.showUpdateForm(curvePoint.getId(), model);

		assertEquals("curvePoint/update", result );

	}

	@Test
	@Order(5)
	public void testUpdateBid() {
		CurvePoint newCurvePoint = new CurvePoint();

		Instant instant = Instant.now();
		Timestamp timestamp = Timestamp.from(instant);

		curvePoint.setCurveId(2);
		curvePoint.setAsOfDate(timestamp);
		curvePoint.setTerm(5.0);
		curvePoint.setValue(5.0);
		curvePoint.setCreationDate(timestamp);

		Model model = mock(Model.class);
		BindingResult bindingResult = mock(BindingResult.class);

		String result = curveService.updateBid(curvePoint.getId(), newCurvePoint, bindingResult, model);

		assertEquals("redirect:/curvePoint/list", result );

	}

	@Test
	@Order(6)
	public void testDeleteBid() {
		Model model = mock(Model.class);
		String result = curveService.deleteBid(curvePoint.getId(), model);
		assertEquals("redirect:/curvePoint/list", result );

	}
}
