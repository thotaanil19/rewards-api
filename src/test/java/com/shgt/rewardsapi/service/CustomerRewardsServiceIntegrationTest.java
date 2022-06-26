package com.shgt.rewardsapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shgt.rewardsapi.dto.RewardsApiResponse;

@SpringBootTest
class CustomerRewardsServiceIntegrationTest {

	@Autowired
	private CustomerRewardsService customerRewardsService;

	@Test
	void testGetCustomerRewards() {
		RewardsApiResponse rewardsApiResponse = customerRewardsService.getCustomerRewards(2L);
		Assertions.assertNotNull(rewardsApiResponse);
		Assertions.assertEquals(2L, rewardsApiResponse.getCustomerId());
		Assertions.assertNotEquals(0D, rewardsApiResponse.getTotalRewardPoints());
		Assertions.assertNotNull(rewardsApiResponse.getMonthlyRewardPoints());
	}

	@Test
	void testGetCustomerRewards_InvalidCustomerId() {
		RewardsApiResponse rewardsApiResponse = customerRewardsService.getCustomerRewards(200L);
		Assertions.assertNotNull(rewardsApiResponse);
		Assertions.assertEquals(200L, rewardsApiResponse.getCustomerId());
		Assertions.assertEquals(0D, rewardsApiResponse.getTotalRewardPoints());
	}

}
