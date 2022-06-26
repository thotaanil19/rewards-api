package com.shgt.rewardsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthlyRewardsInfo {
	
	@JsonProperty("month")
	private String month;
	
	@JsonProperty("reward-points")
	private Double rewardPoints;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(Double rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

}
