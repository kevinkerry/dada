package com.youyisi.admin.domain.hugthigh;

import com.youyisi.admin.domain.user.User;
import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-07-19
 */

public class HugThigh extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9185827857937339803L;
	private Long thighId; //
	private Long userId; //
	private Long activityId; //
	private Integer payType; //
	private Integer payStatus; //
	private Integer status; //
	private Long createTime; //

	private User user;

	private Double commission;

	public HugThigh() {

	}

	public HugThigh(Long userId, Long activityId) {
		this.userId = userId;
		this.activityId = activityId;
	}

	public HugThigh(Long thighId, Long userId, Long activityId) {
		this.thighId = thighId;
		this.userId = userId;
		this.activityId = activityId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setThighId(Long thighId) {
		this.thighId = thighId;
	}

	public Long getThighId() {
		return thighId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}
}
