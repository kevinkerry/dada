package com.youyisi.sports.domain.relay;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-09-05
 */


public class RelayTeam extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5946307494339981472L;
	private Long activityId; //
	private String name; //
	private String logo; //
	private Long userId; //
	private Integer status; //
	private Long createTime; //
	private Long updateTime; //
	
	public void setActivityId(Long activityId){
		this.activityId=activityId;
	}
	public Long getActivityId(){
		return activityId;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setLogo(String logo){
		this.logo=logo;
	}
	public String getLogo(){
		return logo;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
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
	public void setUpdateTime(Long updateTime){
		this.updateTime=updateTime;
	}
	public Long getUpdateTime(){
		return updateTime;
	}
}

