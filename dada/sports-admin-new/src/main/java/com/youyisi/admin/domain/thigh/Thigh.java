package com.youyisi.admin.domain.thigh;

import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.user.User;
import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-07-18
 */

public class Thigh extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -315322973191371198L;
	private Long userId; //
	private Long activityId; //
	private Integer type; //
	private Integer status; //
	private Long createTime; //
	private Long date; //

	private User user;
	private Integer hugNum;
	private Double hugMoney;

	private Activity activity;

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

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
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

	public void setDate(Long date) {
		this.date = date;
	}

	public Long getDate() {
		return date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getHugNum() {
		return hugNum;
	}

	public void setHugNum(Integer hugNum) {
		this.hugNum = hugNum;
	}

	public Double getHugMoney() {
		return hugMoney;
	}

	public void setHugMoney(Double hugMoney) {
		this.hugMoney = hugMoney;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}
