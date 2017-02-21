package com.youyisi.sports.domain.coupon;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-07-11
 */


public class Coupon extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8070875963402350782L;
	private Long activityId; //
	private Double annualYield; //
	private Double bonus; //
	private Integer type; //
	private Integer expiryDay; //
	private Double distance; //
	private Double commission; //
	private Double commissionRate; //
	
	public void setActivityId(Long activityId){
		this.activityId=activityId;
	}
	public Long getActivityId(){
		return activityId;
	}
	public void setAnnualYield(Double annualYield){
		this.annualYield=annualYield;
	}
	public Double getAnnualYield(){
		return annualYield;
	}
	public void setBonus(Double bonus){
		this.bonus=bonus;
	}
	public Double getBonus(){
		return bonus;
	}
	public void setType(Integer type){
		this.type=type;
	}
	public Integer getType(){
		return type;
	}
	public void setExpiryDay(Integer expiryDay){
		this.expiryDay=expiryDay;
	}
	public Integer getExpiryDay(){
		return expiryDay;
	}
	public void setDistance(Double distance){
		this.distance=distance;
	}
	public Double getDistance(){
		return distance;
	}
	public void setCommission(Double commission){
		this.commission=commission;
	}
	public Double getCommission(){
		return commission;
	}
	public void setCommissionRate(Double commissionRate){
		this.commissionRate=commissionRate;
	}
	public Double getCommissionRate(){
		return commissionRate;
	}
}

