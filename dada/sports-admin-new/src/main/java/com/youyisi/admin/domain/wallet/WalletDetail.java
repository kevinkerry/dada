package com.youyisi.admin.domain.wallet;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-05-14
 */

public class WalletDetail extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6345093388953951831L;
	private Long userId; //
	private Integer type; //
	private Double money; //
	private Long createTime; //
	private Long date; //
	private Double result; //

	/** 附加字段 总资产 **/
	private Double totalAsset;

	/** 当天收益 **/
	private Double annualYield;

	public WalletDetail() {

	}

	public WalletDetail(Long userId, Integer type, Long date) {
		this.userId = userId;
		this.type = type;
		this.date = date;
	}

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

	public void setDate(Long date) {
		this.date = date;
	}

	public Long getDate() {
		return date;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public Double getTotalAsset() {
		return totalAsset;
	}

	public void setTotalAsset(Double totalAsset) {
		this.totalAsset = totalAsset;
	}

	public Double getAnnualYield() {
		return annualYield;
	}

	public void setAnnualYield(Double annualYield) {
		this.annualYield = annualYield;
	}
}
