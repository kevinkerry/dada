package com.youyisi.admin.domain.withdraw;

import com.youyisi.admin.domain.alipay.Alipay;
import com.youyisi.admin.domain.user.User;
import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-05-21
 */

public class Withdraw extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3728352575776713710L;
	private Long userId; //
	private String withdrawNumber; //
	private Double money; //
	private Integer status; //
	private Long createTime; //
	private Long updateTime; //
	private String note; //
	private Double principal;

	/** 附加字段 用户资产 **/
	private Double totalAsset = 0.0;
	/** 附加字段 用户本金 **/
	private Double walletPrincipal = 0.0;
	/** 附加字段 **/
	private Alipay alipay;

	/** 附加字段 用户余额 **/
	private Double balance = 0.0;

	/** 附加字段 **/
	private User user;

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

	public Alipay getAlipay() {
		return alipay;
	}

	public void setAlipay(Alipay alipay) {
		this.alipay = alipay;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getPrincipal() {
		return principal;
	}

	public void setPrincipal(Double principal) {
		this.principal = principal;
	}

	public Double getTotalAsset() {
		return totalAsset;
	}

	public void setTotalAsset(Double totalAsset) {
		this.totalAsset = totalAsset;
	}

	public Double getWalletPrincipal() {
		return walletPrincipal;
	}

	public void setWalletPrincipal(Double walletPrincipal) {
		this.walletPrincipal = walletPrincipal;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Withdraw [userId=" + userId + ", withdrawNumber=" + withdrawNumber + ", money=" + money + ", status="
				+ status + ", createTime=" + createTime + ", updateTime=" + updateTime + ", note=" + note
				+ ", principal=" + principal + ", totalAsset=" + totalAsset + ", alipay=" + alipay + ", user=" + user
				+ "]";
	}

}
