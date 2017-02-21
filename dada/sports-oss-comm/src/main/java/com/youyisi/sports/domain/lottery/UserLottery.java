package com.youyisi.sports.domain.lottery;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-10-21
 */


public class UserLottery extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6766195441562226691L;
	private Long userId; //
	private Long lotteryId; //
	private String myNumber; //
	private Long betCountId; //
	private Integer payStatus; //
	private Integer status; //
	private Long createTime; //
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setLotteryId(Long lotteryId){
		this.lotteryId=lotteryId;
	}
	public Long getLotteryId(){
		return lotteryId;
	}
	public void setMyNumber(String myNumber){
		this.myNumber=myNumber;
	}
	public String getMyNumber(){
		return myNumber;
	}
	public void setBetCountId(Long betCountId){
		this.betCountId=betCountId;
	}
	public Long getBetCountId(){
		return betCountId;
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
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
}

