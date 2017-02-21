package com.youyisi.admin.domain.gold;

import java.util.List;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-10-26
 */

public class GoldBeanRecharge extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8928333336756535138L;
	private Double money; //
	private Double goldBean; //
	private Integer orders; //

	private List<GoldBeanRecharge> goldBeanRechargeList;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getMoney() {
		return money;
	}

	public void setGoldBean(Double goldBean) {
		this.goldBean = goldBean;
	}

	public Double getGoldBean() {
		return goldBean;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public Integer getOrders() {
		return orders;
	}

	public List<GoldBeanRecharge> getGoldBeanRechargeList() {
		return goldBeanRechargeList;
	}

	public void setGoldBeanRechargeList(List<GoldBeanRecharge> goldBeanRechargeList) {
		this.goldBeanRechargeList = goldBeanRechargeList;
	}
}
