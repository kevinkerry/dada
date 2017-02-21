package com.youyisi.admin.domain.experience;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-05-14
 */


public class ExperienceAccount  extends BaseObject<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 152681364847904017L;
	private Long userId; //
	private Double money; //
	private Long createTime; //
	private Long expiryTime; //
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setMoney(Double money){
		this.money=money;
	}
	public Double getMoney(){
		return money;
	}
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
	public void setExpiryTime(Long expiryTime){
		this.expiryTime=expiryTime;
	}
	public Long getExpiryTime(){
		return expiryTime;
	}
}

