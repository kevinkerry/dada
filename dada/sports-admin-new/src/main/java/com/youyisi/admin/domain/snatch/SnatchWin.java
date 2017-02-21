package com.youyisi.admin.domain.snatch;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-09-22
 */

public class SnatchWin extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2495628837482939060L;
	private String activityNum; //
	private Long activityId; //
	private Long userId; //
	private Double bonus; //
	private Integer status; //
	private Long createTime; //

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setActivityNum(String activityNum) {
		this.activityNum = activityNum;
	}

	public String getActivityNum() {
		return activityNum;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}
}
