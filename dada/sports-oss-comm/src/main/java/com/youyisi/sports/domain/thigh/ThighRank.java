package com.youyisi.sports.domain.thigh;

import com.youyisi.sports.domain.BaseEntity;
import com.youyisi.sports.domain.user.UserLessInfo;

/**
 * @author shuye
 * @time 2016-07-11
 */


public class ThighRank extends BaseEntity implements Comparable<ThighRank>{
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
	private UserLessInfo user;
	private Double hugThighMoney;
	private Long hugThighCount;

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
	public UserLessInfo getUser() {
		return user;
	}
	public void setUser(UserLessInfo user) {
		this.user = user;
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
	@Override
	public int compareTo(ThighRank o) {
		double thismoney = 0.0;
		double othermoney = 0.0;
		if(this.getHugThighMoney()!=null){
			thismoney=this.getHugThighMoney();
		}
		if(o.getHugThighMoney()!=null){
			othermoney=o.getHugThighMoney();
		}
		if(thismoney>othermoney){
			return -1;
		}else{
			return 1;
		}
	}
}

