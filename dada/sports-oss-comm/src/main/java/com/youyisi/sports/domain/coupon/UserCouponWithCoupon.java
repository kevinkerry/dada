package com.youyisi.sports.domain.coupon;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-07-11
 */


public class UserCouponWithCoupon extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1706116466331346893L;
	private Long userId; //
	private Long couponId; //
	private Long createTime; //
	private Long expiryTime; //
	private Integer status; //
	private Long date; //
	private Long updateTime; //
	private Integer category; //
	private Coupon coupon;
	private Double income;
	
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setCouponId(Long couponId){
		this.couponId=couponId;
	}
	public Long getCouponId(){
		return couponId;
	}
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
	public void setExpiryTime(Long expiryTime){
		this.expiryTime=expiryTime;
	}
	public Long getExpiryTime(){
		return expiryTime;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setDate(Long date){
		this.date=date;
	}
	public Long getDate(){
		return date;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
}

