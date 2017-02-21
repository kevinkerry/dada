package com.youyisi.sports.domain.step;

import com.youyisi.sports.domain.BaseEntity;
import com.youyisi.sports.domain.user.User;

/**
 * @author shuye
 * @time 2016-05-12
 */

public class StepWithUser extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2848165974761604213L;
	private Long userId; //
	private Integer step; //
	private Integer status; //
	private Long date; //
	private Long createTime; //
	private Long updateTime; //
	private User user;
	

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Integer getStep() {
		return step;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Long getDate() {
		return date;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	@Override
	public String toString() {
		return "Step [userId=" + userId + ", step=" + step + ", date=" + date + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
