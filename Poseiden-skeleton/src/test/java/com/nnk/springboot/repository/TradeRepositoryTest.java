package com.nnk.springboot.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class TradeRepositoryTest {

	@Autowired
	private TradeRepository tradeRepository;
	
	@Test
	@Order(1)
	public void testFindAll() {
		
		List<Trade> trade = tradeRepository.findAll();
		
		assertTrue(trade.size()> 0);
	}
	
}
