package com.nnk.springboot.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.time.Instant;
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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurveRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class CurveRepositoryTest {
	
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
		
		curveRepository.save(curvePoint);
	}

	@AfterAll
	void cleanUp() {
		curveRepository.deleteAll();
	}
	
	
	@Test
	@Order(1)
	public void testFindAll() {
		
		List<CurvePoint> curve = curveRepository.findAll();
		CurvePoint result = curve.get(0);
		assertEquals(result.getId(), curvePoint.getId());
	}
}
