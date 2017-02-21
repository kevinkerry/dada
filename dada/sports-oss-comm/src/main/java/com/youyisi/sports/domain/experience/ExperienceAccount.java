package com.youyisi.sports.domain.experience;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-05-12
 */

public class ExperienceAccount extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3608889147152975421L;
	private Long userId; //
	private Double money; //
	private Long createTime; //
	private Long expiryTime; //
	private Integer level;


	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getMoney() {
		return money;
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

	@Override
	public String toString() {
		return "ExperienceAccount [userId=" + userId + ", money=" + money + ", createTime=" + createTime
				+ ", expiryTime=" + expiryTime + "]";
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
