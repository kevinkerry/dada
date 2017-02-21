package com.youyisi.sports.domain.thigh;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-07-13
 */


public class HugThighActivity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7301540076042720549L;
	private Long activityId; //
	private Integer thighLimit; //
	private Integer hugThighLimit; //
	private Double avspeed; //
	private Double maxspeed; //
	private Double stride; //
	private String rule; //
	private String message; //
	private String cronExpression; //
	private Long createTime; //
	private Long updateTime; //
	
	public void setActivityId(Long activityId){
		this.activityId=activityId;
	}
	public Long getActivityId(){
		return activityId;
	}
	public void setThighLimit(Integer thighLimit){
		this.thighLimit=thighLimit;
	}
	public Integer getThighLimit(){
		return thighLimit;
	}
	public void setHugThighLimit(Integer hugThighLimit){
		this.hugThighLimit=hugThighLimit;
	}
	public Integer getHugThighLimit(){
		return hugThighLimit;
	}
	public void setAvspeed(Double avspeed){
		this.avspeed=avspeed;
	}
	public Double getAvspeed(){
		return avspeed;
	}
	public void setMaxspeed(Double maxspeed){
		this.maxspeed=maxspeed;
	}
	public Double getMaxspeed(){
		return maxspeed;
	}
	public void setStride(Double stride){
		this.stride=stride;
	}
	public Double getStride(){
		return stride;
	}
	public void setRule(String rule){
		this.rule=rule;
	}
	public String getRule(){
		return rule;
	}
	public void setMessage(String message){
		this.message=message;
	}
	public String getMessage(){
		return message;
	}
	public void setCronExpression(String cronExpression){
		this.cronExpression=cronExpression;
	}
	public String getCronExpression(){
		return cronExpression;
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
}

