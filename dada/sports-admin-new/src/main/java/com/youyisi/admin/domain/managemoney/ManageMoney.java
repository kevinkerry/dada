package com.youyisi.admin.domain.managemoney;

import java.io.Serializable;

/**
 * 
 * @author hetao
 * @date 2016年6月23日 下午5:16:13
 * @version 1.0
 * @parameter
 * @return
 */
public class ManageMoney implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5701309226106104759L;

	/** 当前年化收益 **/
	private Double annualYield;
	/** 总资产 **/
	private Double totalAsset;
	/** 累计收益 **/
	private Double income;
	/** 本金 **/
	private Double principal;
	/** 收益余额 **/
	private Double earningsBalance = 0.00;// ArithHelper.add(totalAsset,principal);
	/** 总体验金 **/
	private String experienceMoney;
	/** 待提现金额 **/
	private Double stayWithdraw;

	public Double getAnnualYield() {
		return annualYield;
	}

	public void setAnnualYield(Double annualYield) {
		this.annualYield = annualYield;
	}

	public Double getTotalAsset() {
		return totalAsset;
	}

	public void setTotalAsset(Double totalAsset) {
		this.totalAsset = totalAsset;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getPrincipal() {
		return principal;
	}

	public void setPrincipal(Double principal) {
		this.principal = principal;
	}

	public Double getEarningsBalance() {
		return earningsBalance;
	}

	public void setEarningsBalance(Double earningsBalance) {
		this.earningsBalance = earningsBalance;
	}

	public Double getStayWithdraw() {
		return stayWithdraw;
	}

	public void setStayWithdraw(Double stayWithdraw) {
		this.stayWithdraw = stayWithdraw;
	}

	public String getExperienceMoney() {
		return experienceMoney;
	}

	public void setExperienceMoney(String experienceMoney) {
		this.experienceMoney = experienceMoney;
	}
}
