package com.youyisi.sports.domain.thigh;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-07-11
 */


public class MyThigh extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1216730412199606747L;
	private Long userId; //
	private Long activityId; //
	private Integer type; //
	private Integer status; //
	private Long createTime; //
	private Long date; //
	private Long hugThighCount;
	private Double hugThighMoney;

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
	public void setType(Integer type){
		this.type=type;
	}
	public Integer getType(){
		return type;
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
	public void setDate(Long date){
		this.date=date;
	}
	public Long getDate(){
		return date;
	}
	public Long getHugThighCount() {
		return hugThighCount;
	}
	public void setHugThighCount(Long hugThighCount) {
		this.hugThighCount = hugThighCount;
	}
	public Double getHugThighMoney() {
		return hugThighMoney;
	}
	public void setHugThighMoney(Double hugThighMoney) {
		this.hugThighMoney = hugThighMoney;
	}
}

