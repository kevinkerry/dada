package com.youyisi.sports.domain.snatch;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-09-21
 */


public class UserSnatch extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8212088678991321747L;
	private Long activityId; //
	private Long userId; //
	private Long snatchFeeId; //
	private Integer payStatus; //
	private Integer status; //
	private Long createTime; //
	
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
	public void setPayStatus(Integer payStatus){
		this.payStatus=payStatus;
	}
	public Integer getPayStatus(){
		return payStatus;
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
	public Long getSnatchFeeId() {
		return snatchFeeId;
	}
	public void setSnatchFeeId(Long snatchFeeId) {
		this.snatchFeeId = snatchFeeId;
	}
}

