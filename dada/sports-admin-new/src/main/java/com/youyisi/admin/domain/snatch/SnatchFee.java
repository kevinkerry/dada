package com.youyisi.admin.domain.snatch;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-09-22
 */

public class SnatchFee extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7802165182994082350L;
	private Long activityId; //
	private Integer count; //
	private Double money; //

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getMoney() {
		return money;
	}
}
