package com.youyisi.admin.domain.user;

import com.youyisi.admin.domain.coupon.Coupon;
import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-07-19
 */

public class UserCoupon extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8203060843918220048L;
	private Long userId; //
	private Long couponId; //
	private Long updateTime; //
	private Long createTime; //
	private Long expiryTime; //
	private Integer status; //
	private Long date; //
	private Integer category;

	private Coupon coupon; //

	private Double earnings;

	private String nickname;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setExpiryTime(Long expiryTime) {
		this.expiryTime = expiryTime;
	}

	public Long getExpiryTime() {
		return expiryTime;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Long getDate() {
		return date;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Double getEarnings() {
		return earnings;
	}

	public void setEarnings(Double earnings) {
		this.earnings = earnings;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}
}
