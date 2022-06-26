package com.shgt.rewardsapi.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.shgt.rewardsapi.dao.OrdersRepository;
import com.shgt.rewardsapi.dao.RewardsConfigurationRepository;
import com.shgt.rewardsapi.dto.MonthlyRewardsInfo;
import com.shgt.rewardsapi.dto.RewardsApiResponse;
import com.shgt.rewardsapi.entites.Orders;
import com.shgt.rewardsapi.entites.RewardsConfiguration;
import com.shgt.rewardsapi.exception.NoOrdersFoundException;
import com.shgt.rewardsapi.exception.RewardsConfigationNotFoundException;
import com.shgt.rewardsapi.util.Util;

@Service
public class CustomerRewardsService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerRewardsService.class);

	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private RewardsConfigurationRepository rewardsConfigurationRepository;

	/**
	 * Calculate monthly, total rewards for a customer
	 * 
	 * @param customerId
	 * @return RewardsApiResponse
	 */
	public RewardsApiResponse getCustomerRewards(Long customerId) {
		LOG.info("Start: getting Rewards for customer id: {}", customerId);
		// 1. Get all the customer rewards
		List<Orders> orders = ordersRepository.findAllByCustomerId(customerId);
		if (CollectionUtils.isEmpty(orders)) {
			LOG.info("No Orders found for customer id: {} ", customerId);
			throw new NoOrdersFoundException("No Oders found for customer id: " + customerId);
		}
		// 2. Get the Rewards configuration data
		List<RewardsConfiguration> rewardsConfigurations = rewardsConfigurationRepository.findAll();
		if (CollectionUtils.isEmpty(rewardsConfigurations)) {
			LOG.error("No Rewards configuration found in DB ");
			throw new RewardsConfigationNotFoundException("No Rewards configuration found in DB");
		}
		// 3. Calculate total rewards points
		Double totalRewards = orders.stream().map(Orders::getPrice).map(e -> Util.calculateRewards(e, rewardsConfigurations))
				.collect(Collectors.summingDouble(Double::doubleValue));
		// 4. Calculate monthly rewards points
		List<MonthlyRewardsInfo> monthlyRewards = new ArrayList<>();
		orders.stream()
				.collect(Collectors.toMap(o -> Util.localDateToMonthAndYear(o.getOrderDate()),
						o -> Util.calculateRewards(o.getPrice(), rewardsConfigurations), (oldValue, newValue) -> oldValue + newValue,
						LinkedHashMap::new))
				.forEach((month, rewards) -> {
					MonthlyRewardsInfo monthlyReward = new MonthlyRewardsInfo();
					monthlyReward.setMonth(month);
					monthlyReward.setRewardPoints(rewards);
					monthlyRewards.add(monthlyReward);
				});
		// 5. Build RewardsApiResponse object
		RewardsApiResponse rewardsApiResponse = new RewardsApiResponse();
		rewardsApiResponse.setCustomerId(customerId);
		rewardsApiResponse.setTotalRewardPoints(totalRewards);
		rewardsApiResponse.setMonthlyRewardPoints(monthlyRewards);
		LOG.info("End: getting Rewards for customer id: {}", customerId);
		return rewardsApiResponse;
	}

}
