package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;


@Controller
public class BidListController {

	 @Autowired
	    private BidListRepository bidListRepository;
	
    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        model.addAttribute("bidList", bidListRepository.findAll());
        return "bidList/list";
    }
    
    /**
  	 * Get all bidList
  	 * @return "bidList/add"
  	 */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    /**
  	 * Post for validate bidList 
  	 * @RequestBody BidList : bidList
  	 * @return "redirect:/bidList/list"
  	 */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
    	if(!result.hasErrors()) {
    		bidListRepository.save(bid);
            model.addAttribute("bidList", bidListRepository.findAll());
            return "redirect:/bidList/list";

    	}
        return "bidList/add";
    }

    /**
   	 * Update one bidList by id
   	 * @Param id : id
   	 * @return "bidList/update"
   	 */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	BidList bid = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        model.addAttribute("bidList", bid);
        return "bidList/update";
    }

    /**
  	 * Update one bidList by id
  	 * @Param id : id
  	 * @return "redirect:/bidList/list"
  	 */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
    	  if (result.hasErrors()) {
              return "bidList/update";
          }
    	  bidListRepository.save(bidList);
          model.addAttribute("bidList", bidListRepository.findAll());
        return "redirect:/bidList/list";
    }

    /**
   	 * Delete one bidList by id
   	 * @Param id : id
   	 * @return "redirect:/bidList/list"
   	 */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	BidList bid = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
    	bidListRepository.delete(bid);
        model.addAttribute("bidList", bidListRepository.findAll());
    	return "redirect:/bidList/list";
    }
}
