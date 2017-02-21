package com.youyisi.sports.domain.snatch;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-09-21
 */


public class SnatchWin extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7752262092519658469L;
	private String activityNum; //
	private Long activityId; //
	private Long userId; //
	private Double bonus; //
	private Integer status; //
	private Long createTime; //

	public void setActivityNum(String activityNum){
		this.activityNum=activityNum;
	}
	public String getActivityNum(){
		return activityNum;
	}
	public void setActivityId(Long activityId){
		this.activityId=activityId;
	}
	public Long getActivityId(){
		return activityId;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setBonus(Double bonus){
		this.bonus=bonus;
	}
	public Double getBonus(){
		return bonus;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
}

