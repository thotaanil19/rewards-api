package com.shgt.rewardsapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RewardsApiResponse {

	@JsonProperty("customer-id")
	private Long customerId;

	@JsonProperty("total-reward-points")
	private Double totalRewardPoints;

	@JsonProperty("monthly-reward-points")
	private List<MonthlyRewardsInfo> monthlyRewardPoints;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Double getTotalRewardPoints() {
		return totalRewardPoints;
	}

	public void setTotalRewardPoints(Double totalRewardPoints) {
		this.totalRewardPoints = totalRewardPoints;
	}

	public List<MonthlyRewardsInfo> getMonthlyRewardPoints() {
		return monthlyRewardPoints;
	}

	public void setMonthlyRewardPoints(List<MonthlyRewardsInfo> monthlyRewardPoints) {
		this.monthlyRewardPoints = monthlyRewardPoints;
	}

	

}
