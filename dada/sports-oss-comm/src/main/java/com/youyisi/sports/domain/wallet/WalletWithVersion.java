package com.youyisi.sports.domain.wallet;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-05-12
 */

public class WalletWithVersion extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1337836836715390626L;
	private Long userId; //
	private Double totalAsset; //
	private Double principal; //
	private Double income; //
	private Double version; //


	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setTotalAsset(Double totalAsset) {
		this.totalAsset = totalAsset;
	}

	public Double getTotalAsset() {
		return totalAsset;
	}

	public void setPrincipal(Double principal) {
		this.principal = principal;
	}

	public Double getPrincipal() {
		return principal;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getIncome() {
		return income;
	}

	@Override
	public String toString() {
		return "Wallet [userId=" + userId + ", totalAsset=" + totalAsset + ", principal=" + principal + ", income="
				+ income + "]";
	}

	public Double getVersion() {
		return version;
	}

	public void setVersion(Double version) {
		this.version = version;
	}

}
