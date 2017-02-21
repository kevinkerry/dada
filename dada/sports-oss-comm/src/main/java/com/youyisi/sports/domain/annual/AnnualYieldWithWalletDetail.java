package com.youyisi.sports.domain.annual;

import com.youyisi.sports.domain.BaseEntity;
import com.youyisi.sports.domain.wallet.WalletDetail;

/**
 * @author shuye
 * @time 2016-05-12
 */

public class AnnualYieldWithWalletDetail extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8168856507403330485L;
	private Long userId; //
	private Long date; //
	private Double annualYield; //
	private Double baseAnnualYield; //
	private Double activityAnnualYield;
	private Long createTime; //
	private Long updateTime; //
	private WalletDetail walletDetail;


	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Long getDate() {
		return date;
	}

	public void setAnnualYield(Double annualYield) {
		this.annualYield = annualYield;
	}

	public Double getAnnualYield() {
		return annualYield;
	}

	public void setBaseAnnualYield(Double baseAnnualYield) {
		this.baseAnnualYield = baseAnnualYield;
	}

	public Double getBaseAnnualYield() {
		return baseAnnualYield;
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
		return "AnnualYield [userId=" + userId + ", date=" + date + ", annualYield=" + annualYield
				+ ", baseAnnualYield=" + baseAnnualYield + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

	public WalletDetail getWalletDetail() {
		return walletDetail;
	}

	public void setWalletDetail(WalletDetail walletDetail) {
		this.walletDetail = walletDetail;
	}

	public Double getActivityAnnualYield() {
		return activityAnnualYield;
	}

	public void setActivityAnnualYield(Double activityAnnualYield) {
		this.activityAnnualYield = activityAnnualYield;
	}

}
