package com.shgt.rewardsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shgt.rewardsapi.dto.RewardsApiResponse;
import com.shgt.rewardsapi.service.CustomerRewardsService;

@RestController
public class CustomerRewardsController {
	
	@Autowired
	private CustomerRewardsService customerRewardsService;
	
	@GetMapping("/customer/{id}/rewards")
	public RewardsApiResponse getCustomerRewards(@PathVariable Long id) {
		return customerRewardsService.getCustomerRewards(id);
	}

}
