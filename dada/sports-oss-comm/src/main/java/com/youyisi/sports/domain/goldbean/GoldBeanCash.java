package com.youyisi.sports.domain.goldbean;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-10-21
 */


public class GoldBeanCash extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7479153489107704597L;
	private Long userId; //
	private Long date; //
	private Double goldBean; //
	private Integer status; //
	private Double annualYield; //
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setDate(Long date){
		this.date=date;
	}
	public Long getDate(){
		return date;
	}
	public void setGoldBean(Double goldBean){
		this.goldBean=goldBean;
	}
	public Double getGoldBean(){
		return goldBean;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setAnnualYield(Double annualYield){
		this.annualYield=annualYield;
	}
	public Double getAnnualYield(){
		return annualYield;
	}
}

