package com.youyisi.sports.domain.snatch;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-09-21
 */


public class SnatchFee extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 175973172197685545L;
	private Long activityId; //
	private Integer count; //
	private Double money; //
	
	public void setActivityId(Long activityId){
		this.activityId=activityId;
	}
	public Long getActivityId(){
		return activityId;
	}
	public void setCount(Integer count){
		this.count=count;
	}
	public Integer getCount(){
		return count;
	}
	public void setMoney(Double money){
		this.money=money;
	}
	public Double getMoney(){
		return money;
	}
}

