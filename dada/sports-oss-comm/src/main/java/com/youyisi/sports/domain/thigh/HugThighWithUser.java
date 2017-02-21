package com.youyisi.sports.domain.thigh;

import com.youyisi.sports.domain.BaseEntity;
import com.youyisi.sports.domain.user.UserLessInfo;

/**
 * @author shuye
 * @time 2016-07-11
 */


public class HugThighWithUser extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6530671332287782102L;
	private Long thighId; //
	private Long userId; //
	private Long activityId; //
	private Integer payType; //
	private Integer payStatus; //
	private Integer status; //
	private Long createTime; //
	private UserLessInfo user;
	
	public void setThighId(Long thighId){
		this.thighId=thighId;
	}
	public Long getThighId(){
		return thighId;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setActivityId(Long activityId){
		this.activityId=activityId;
	}
	public Long getActivityId(){
		return activityId;
	}
	public void setPayType(Integer payType){
		this.payType=payType;
	}
	public Integer getPayType(){
		return payType;
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
	public UserLessInfo getUser() {
		return user;
	}
	public void setUser(UserLessInfo user) {
		this.user = user;
	}
}

