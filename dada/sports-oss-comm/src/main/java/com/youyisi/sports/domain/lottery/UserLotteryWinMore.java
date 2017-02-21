package com.youyisi.sports.domain.lottery;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-10-21
 */


public class UserLotteryWinMore extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 264889742659694204L;
	private Long userId; //
	private Long lotteryId; //
	private Long lotteryWinId; //
	private Long createTime; //
	private Integer status; //
	private Long updateTime; //
	private Long expiryTime;
	private LotteryWin lotteryWin;
	private  String lotteryNum;
	
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
	public void setLotteryWinId(Long lotteryWinId){
		this.lotteryWinId=lotteryWinId;
	}
	public Long getLotteryWinId(){
		return lotteryWinId;
	}
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setUpdateTime(Long updateTime){
		this.updateTime=updateTime;
	}
	public Long getUpdateTime(){
		return updateTime;
	}
	public Long getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(Long expiryTime) {
		this.expiryTime = expiryTime;
	}
	public LotteryWin getLotteryWin() {
		return lotteryWin;
	}
	public void setLotteryWin(LotteryWin lotteryWin) {
		this.lotteryWin = lotteryWin;
	}
	public String getLotteryNum() {
		return lotteryNum;
	}
	public void setLotteryNum(String lotteryNum) {
		this.lotteryNum = lotteryNum;
	}
}

