package com.youyisi.admin.domain.relay;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-10-10
 */

public class RelayLine extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5371935889527099990L;
	private Long activityId; //
	private String address; //
	private Double distance; //
	private Integer orders; //

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

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getDistance() {
		return distance;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public Integer getOrders() {
		return orders;
	}
}
