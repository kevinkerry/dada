package com.youyisi.sports.domain.annual;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-05-12
 */

public class AnnualYieldDetail extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 769140969177480268L;
	private Long userId; //
	private Double annualYieldIncrease; //
	private Long createTime; //
	private Integer type; //
	private Long runId; //
	private Long date; //
	

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public Double getAnnualYieldIncrease() {
		return annualYieldIncrease;
	}

	public void setAnnualYieldIncrease(Double annualYieldIncrease) {
		this.annualYieldIncrease = annualYieldIncrease;
	}

	public Long getRunId() {
		return runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}
}
