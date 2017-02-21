package com.youyisi.sports.domain.goldbean;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-10-21
 */


public class UserGoldBean extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1450941636861346255L;
	private Long userId; //
	private Long goldBeanRechargeId; //
	private Long createTime; //
	private Integer payStatus; //
	private Integer status; //
	private GoldBeanRecharge goldBeanRecharge;
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setGoldBeanRechargeId(Long goldBeanRechargeId){
		this.goldBeanRechargeId=goldBeanRechargeId;
	}
	public Long getGoldBeanRechargeId(){
		return goldBeanRechargeId;
	}
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
	public void setPayStatus(Integer payStatus){
		this.payStatus=payStatus;
	}
	public Integer getPayStatus(){
		return payStatus;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public GoldBeanRecharge getGoldBeanRecharge() {
		return goldBeanRecharge;
	}
	public void setGoldBeanRecharge(GoldBeanRecharge goldBeanRecharge) {
		this.goldBeanRecharge = goldBeanRecharge;
	}
}

