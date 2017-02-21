package com.youyisi.sports.domain.activity;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-08-09
 */


public class InviteFriendActivity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4318633722123600278L;
	private Long activityId; //
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

