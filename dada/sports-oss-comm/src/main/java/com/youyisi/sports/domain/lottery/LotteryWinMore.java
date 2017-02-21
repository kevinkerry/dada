package com.youyisi.sports.domain.lottery;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-10-21
 */


public class LotteryWinMore extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2361441015657979738L;
	private Long lotteryId; //
	private Integer level; //
	private Double goldBean; //
	private Integer winCount;
	
	public void setLotteryId(Long lotteryId){
		this.lotteryId=lotteryId;
	}
	public Long getLotteryId(){
		return lotteryId;
	}
	public void setLevel(Integer level){
		this.level=level;
	}
	public Integer getLevel(){
		return level;
	}
	public void setGoldBean(Double goldBean){
		this.goldBean=goldBean;
	}
	public Double getGoldBean(){
		return goldBean;
	}
	public Integer getWinCount() {
		return winCount;
	}
	public void setWinCount(Integer winCount) {
		this.winCount = winCount;
	}
}

