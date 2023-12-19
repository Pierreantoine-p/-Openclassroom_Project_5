package com.nnk.springboot.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class BidListRepositoryTest {
	
	@Autowired
	private BidListRepository bidListRepository;

	@Test
	@Order(1)
	public void testFindAll() {
		
		List<BidList> bidList = bidListRepository.findAll();
		
		assertTrue(bidList.size()> 0);
	}
}
