package com.youyisi.admin.domain.annual;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-05-14
 */


public class AnnualYield  extends BaseObject<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2969193757113704160L;
	private Long userId; //
	private Long date; //
	private Double annualYield; //
	private Double baseAnnualYield; //
	private Double activityAnnualYield;
	private Long createTime; //
	private Long updateTime; //
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
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
	public void setAnnualYield(Double annualYield){
		this.annualYield=annualYield;
	}
	public Double getAnnualYield(){
		return annualYield;
	}
	public void setBaseAnnualYield(Double baseAnnualYield){
		this.baseAnnualYield=baseAnnualYield;
	}
	public Double getBaseAnnualYield(){
		return baseAnnualYield;
	}
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
	public void setUpdateTime(Long updateTime){
		this.updateTime=updateTime;
	}
	public Long getUpdateTime(){
		return updateTime;
	}
	public Double getActivityAnnualYield() {
		return activityAnnualYield;
	}
	public void setActivityAnnualYield(Double activityAnnualYield) {
		this.activityAnnualYield = activityAnnualYield;
	}
}

