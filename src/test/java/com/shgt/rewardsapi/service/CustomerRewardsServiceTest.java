package com.shgt.rewardsapi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.shgt.rewardsapi.dao.OrdersRepository;
import com.shgt.rewardsapi.dao.RewardsConfigurationRepository;
import com.shgt.rewardsapi.dto.RewardsApiResponse;
import com.shgt.rewardsapi.entites.Orders;
import com.shgt.rewardsapi.entites.RewardsConfiguration;

class CustomerRewardsServiceTest {

	@Mock
	private OrdersRepository ordersRepository;

	@Mock
	private RewardsConfigurationRepository rewardsConfigurationRepository;

	@InjectMocks
	private CustomerRewardsService customerRewardsService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		// Mock the DB calls
		Mockito.doReturn(rewardsConfigurations()).when(rewardsConfigurationRepository).findAll();
		Mockito.doReturn(orders()).when(ordersRepository).findAllByCustomerId(2L);
	}

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

	private List<RewardsConfiguration> rewardsConfigurations() {
		List<RewardsConfiguration> rewardsConfigurations = new ArrayList<>();
		RewardsConfiguration rewardsConfiguration1 = new RewardsConfiguration();
		rewardsConfiguration1.setId(1L);
		rewardsConfiguration1.setPrice(100D);
		rewardsConfiguration1.setRewards(2L);
		rewardsConfigurations.add(rewardsConfiguration1);

		RewardsConfiguration rewardsConfiguration2 = new RewardsConfiguration();
		rewardsConfiguration2.setId(2L);
		rewardsConfiguration2.setPrice(50D);
		rewardsConfiguration2.setRewards(1L);
		rewardsConfigurations.add(rewardsConfiguration2);
		return rewardsConfigurations;
	}

	private List<Orders> orders() {
		List<Orders> orders = new ArrayList<>();
		Orders order1 = new Orders();
		order1.setId(1L);
		order1.setPrice(120D);
		order1.setCustomerId(2L);
		order1.setOrderDate(LocalDate.now());
		orders.add(order1);

		return orders;
	}

}
