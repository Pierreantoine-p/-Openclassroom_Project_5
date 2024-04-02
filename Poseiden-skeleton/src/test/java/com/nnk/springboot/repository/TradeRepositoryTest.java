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

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class TradeRepositoryTest {




	@Autowired
	private TradeRepository tradeRepository;

	private Trade trade = new Trade();


	@BeforeAll
	void createTrade() {


		trade.setAccount("a");
		trade.setType("a");
		trade.setBuyQuantity(6.0);

		tradeRepository.save(trade);
	}

	@AfterAll
	void cleanUp() {
		tradeRepository.deleteAll();
	}	

	
	@Test
	@Order(1)
	public void testFindAll() {
		
		List<Trade> tradeList = tradeRepository.findAll();
		Trade result = tradeList.get(0);
		assertEquals(result.getAccount(), trade.getAccount());
	}
	
}
