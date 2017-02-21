package com.youyisi.sports.domain.goldbean;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-10-21
 */


public class GoldBean extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6375766509346183722L;
	private Long userId; //
	private Double goldBean; //
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setGoldBean(Double goldBean){
		this.goldBean=goldBean;
	}
	public Double getGoldBean(){
		return goldBean;
	}
}

