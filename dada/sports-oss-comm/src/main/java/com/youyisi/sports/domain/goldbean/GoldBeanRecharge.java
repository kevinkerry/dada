package com.youyisi.sports.domain.goldbean;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-10-21
 */


public class GoldBeanRecharge extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8395262221743875079L;
	private Double money; //
	private Double goldBean; //
	private Integer orders; //
	
	public void setMoney(Double money){
		this.money=money;
	}
	public Double getMoney(){
		return money;
	}
	public void setGoldBean(Double goldBean){
		this.goldBean=goldBean;
	}
	public Double getGoldBean(){
		return goldBean;
	}
	public void setOrders(Integer orders){
		this.orders=orders;
	}
	public Integer getOrders(){
		return orders;
	}
}

