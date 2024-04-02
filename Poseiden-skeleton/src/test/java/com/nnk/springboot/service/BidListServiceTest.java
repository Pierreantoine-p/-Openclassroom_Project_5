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

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class BidListServiceTest {
	

	@Autowired
	private BidListService bidListService;

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
	}


	@AfterAll
	void cleanUp() {
		bidListRepository.deleteAll();
	}
	
	@Test
	@Order(1)
	public void testHome() {

		Model model = mock(Model.class);

		String result = bidListService.home(model);
		assertEquals("bidList/list", result );

	}

	@Test
	@Order(2)
	public void testValidate() {


		BindingResult bindingResult = mock(BindingResult.class);
		Model model = mock(Model.class);

		String result = bidListService.validate(bid, bindingResult, model);

		assertEquals("redirect:/bidList/list", result );

	}

	@Test
	@Order(3)
	public void testShowUpdateForm() {

		Model model = mock(Model.class);
		String result = bidListService.showUpdateForm(bid.getBidListId() , model);

		assertEquals("bidList/update", result );

	}

	@Test
	@Order(4)
	public void testUpdateBid() {
		BidList newBid = new BidList();

		Instant instant = Instant.now();
		Timestamp timestamp = Timestamp.from(instant);
		newBid.setAccount("b");
		newBid.setType("b");

		newBid.setBidQuantity(6.0);
		newBid.setAskQuantity(6.0);
		newBid.setBid(6.0);
		newBid.setAsk(6.0);
		newBid.setBenchmark("b");
		newBid.setBidListDate(timestamp);

		newBid.setCommentary("b");
		newBid.setSecurity("b");
		newBid.setStatus("b");
		newBid.setTrader("b");
		newBid.setBook("b");		
		newBid.setCreationName("b");
		newBid.setCreationDate(timestamp);
		newBid.setRevisionName("b");
		newBid.setRevisionDate(timestamp);
		newBid.setDealName("b");
		newBid.setDealType("b");
		newBid.setSourceListId("b");
		newBid.setSide("b");

		Model model = mock(Model.class);
		BindingResult bindingResult = mock(BindingResult.class);

		String result = bidListService.updateBid(bid.getBidListId(), newBid, bindingResult, model);

		assertEquals("redirect:/bidList/list", result );

	}

	@Test
	@Order(5)
	public void testDeleteBid() {
		Model model = mock(Model.class);
		String result = bidListService.deleteBid(bid.getBidListId(), model);
		assertEquals("redirect:/bidList/list", result );

	}
}
