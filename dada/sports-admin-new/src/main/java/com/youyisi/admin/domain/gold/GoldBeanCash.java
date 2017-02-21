package com.youyisi.admin.domain.gold;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-10-21
 */

public class GoldBeanCash extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -983823151042764145L;
	private Long userId; //
	private Long date; //
	private Double goldBean; //
	private Integer status; //
	private Double annualYield; //

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

	public void setDate(Long date) {
		this.date = date;
	}

	public Long getDate() {
		return date;
	}

	public void setGoldBean(Double goldBean) {
		this.goldBean = goldBean;
	}

	public Double getGoldBean() {
		return goldBean;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setAnnualYield(Double annualYield) {
		this.annualYield = annualYield;
	}

	public Double getAnnualYield() {
		return annualYield;
	}
}
