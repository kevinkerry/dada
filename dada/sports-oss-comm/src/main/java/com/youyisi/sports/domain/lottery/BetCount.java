package com.youyisi.sports.domain.lottery;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-10-21
 */


public class BetCount extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9139823642362653393L;
	private Integer count; //
	private Long lotteryId; //
	
	public void setCount(Integer count){
		this.count=count;
	}
	public Integer getCount(){
		return count;
	}
	public void setLotteryId(Long lotteryId){
		this.lotteryId=lotteryId;
	}
	public Long getLotteryId(){
		return lotteryId;
	}
}

