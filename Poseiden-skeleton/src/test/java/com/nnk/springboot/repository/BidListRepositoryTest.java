package com.nnk.springboot.repository;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class BidListRepositoryTest {
	
	@Autowired
	private BidListRepository bidListRepository;
	
	private BidList bid = new BidList();

	@BeforeAll
	void createbid() {

		Instant instant = Instant.now();
		Timestamp timestamp = Timestamp.from(instant);
		bid.setAccount("a");
		bid.setType("a");

		bid.setBidQuantity(6.0);
		bid.setAskQuantity(6.0);
		bid.setBid(6.0);
		bid.setAsk(6.0);
		bid.setBenchmark("a");
		bid.setBidListDate(timestamp);

		bid.setCommentary("a");
		bid.setSecurity("a");
		bid.setStatus("a");
		bid.setTrader("a");
		bid.setBook("a");		
		bid.setCreationName("a");
		bid.setCreationDate(timestamp);
		bid.setRevisionName("a");
		bid.setRevisionDate(timestamp);
		bid.setDealName("a");
		bid.setDealType("a");
		bid.setSourceListId("a");
		bid.setSide("a");
		
		bidListRepository.save(bid);

	}


	@AfterAll
	void cleanUp() {
		bidListRepository.deleteAll();
	}
	
	@Test
	@Order(1)
	public void testFindAll() {
		
		
		List<BidList> bidList = bidListRepository.findAll();
		BidList result  = bidList.get(0);
		
		assertEquals(result.getAccount(), "a");
		assertEquals(result.getType() , "a");
		assertEquals(result.getBidQuantity(), 6.0);

	}
}
