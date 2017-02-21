package com.youyisi.sports.domain.relay;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-10-08
 */


public class RelayLine extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5578599329269909957L;
	private Long activityId; //
	private String address; //
	private Double distance; //
	private Integer orders; //

	public void setActivityId(Long activityId){
		this.activityId=activityId;
	}
	public Long getActivityId(){
		return activityId;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	public void setDistance(Double distance){
		this.distance=distance;
	}
	public Double getDistance(){
		return distance;
	}
	public void setOrders(Integer orders){
		this.orders=orders;
	}
	public Integer getOrders(){
		return orders;
	}
}

