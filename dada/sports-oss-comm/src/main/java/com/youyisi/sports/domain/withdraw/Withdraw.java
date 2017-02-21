package com.youyisi.sports.domain.withdraw;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-05-12
 */

public class Withdraw extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2190662406962570659L;
	private Long userId; //
	private String withdrawNumber; //
	private Double money; //
	private Double principal;
	private Integer status; //
	private Long createTime; //
	private Long updateTime; //
	private String note; //

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setWithdrawNumber(String withdrawNumber) {
		this.withdrawNumber = withdrawNumber;
	}

	public String getWithdrawNumber() {
		return withdrawNumber;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getMoney() {
		return money;
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

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public Double getPrincipal() {
		return principal;
	}

	public void setPrincipal(Double principal) {
		this.principal = principal;
	}
}
