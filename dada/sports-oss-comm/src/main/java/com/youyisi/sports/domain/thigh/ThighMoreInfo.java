package com.youyisi.sports.domain.thigh;

import com.youyisi.sports.domain.BaseEntity;
import com.youyisi.sports.domain.coupon.Coupon;
import com.youyisi.sports.domain.user.UserLessInfo;

/**
 * @author shuye
 * @time 2016-07-11
 */


public class ThighMoreInfo extends BaseEntity{
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
	private Coupon coupon;
	private Integer hugThighCount;
	private Integer ishug;

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
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public Integer getHugThighCount() {
		return hugThighCount;
	}
	public void setHugThighCount(Integer hugThighCount) {
		this.hugThighCount = hugThighCount;
	}
	public Integer getIshug() {
		return ishug;
	}
	public void setIshug(Integer ishug) {
		this.ishug = ishug;
	}
}

