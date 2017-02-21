package com.youyisi.admin.domain.intelligent.items;

import com.youyisi.lang.domain.BaseObject;

public class SportsItems extends BaseObject<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 996244250956798170L;
	private Long userId;
	private String achievement;
	private String name;
	private String startTime;
	private String endTime;

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
