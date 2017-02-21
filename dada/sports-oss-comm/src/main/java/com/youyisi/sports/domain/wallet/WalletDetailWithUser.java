package com.youyisi.sports.domain.wallet;

import com.youyisi.sports.domain.BaseEntity;
import com.youyisi.sports.domain.user.UserLessInfo;

/**
 * @author shuye
 * @time 2016-05-12
 */

public class WalletDetailWithUser extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3593328950552151452L;
	private Long userId; //
	private Integer type; //
	private Double money; //
	private Long createTime; //
	private Long date; //
	private Double result; //
	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	private UserLessInfo user;
	

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
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

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public UserLessInfo getUser() {
		return user;
	}

	public void setUser(UserLessInfo user) {
		this.user = user;
	}
}
