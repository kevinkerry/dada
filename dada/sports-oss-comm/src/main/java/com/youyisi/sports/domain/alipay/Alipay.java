package com.youyisi.sports.domain.alipay;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-05-19
 */


public class Alipay extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8117948452812931415L;
	private Long userId; //
	private String alipay; //
	private String realName; //
	private Long createTime; //

	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setAlipay(String alipay){
		this.alipay=alipay;
	}
	public String getAlipay(){
		return alipay;
	}
	public void setRealName(String realName){
		this.realName=realName;
	}
	public String getRealName(){
		return realName;
	}
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
}

