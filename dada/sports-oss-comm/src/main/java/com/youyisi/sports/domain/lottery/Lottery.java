package com.youyisi.sports.domain.lottery;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-10-21
 */


public class Lottery extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1200640379119957702L;
	private String name; //
	private String winNumber; //
	private String lotteryNum; //
	private Long date; //
	private String logo; //
	private Double goldBean; //
	private Long beginTime; //
	private Long endTime; //
	private Long lotteryTime; //
	private Integer getExpiryDay; //
	private Integer bonusExpiryDay; //
	private String rule; //
	private String message; //
	private String cronExpression; //
	private Long createTime; //
	private Long updateTime; //
	private Integer settle; //
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setWinNumber(String winNumber){
		this.winNumber=winNumber;
	}
	public String getWinNumber(){
		return winNumber;
	}
	public void setLotteryNum(String lotteryNum){
		this.lotteryNum=lotteryNum;
	}
	public String getLotteryNum(){
		return lotteryNum;
	}
	public void setDate(Long date){
		this.date=date;
	}
	public Long getDate(){
		return date;
	}
	public void setLogo(String logo){
		this.logo=logo;
	}
	public String getLogo(){
		return logo;
	}
	public void setGoldBean(Double goldBean){
		this.goldBean=goldBean;
	}
	public Double getGoldBean(){
		return goldBean;
	}
	public void setBeginTime(Long beginTime){
		this.beginTime=beginTime;
	}
	public Long getBeginTime(){
		return beginTime;
	}
	public void setEndTime(Long endTime){
		this.endTime=endTime;
	}
	public Long getEndTime(){
		return endTime;
	}
	public void setLotteryTime(Long lotteryTime){
		this.lotteryTime=lotteryTime;
	}
	public Long getLotteryTime(){
		return lotteryTime;
	}
	public void setGetExpiryDay(Integer getExpiryDay){
		this.getExpiryDay=getExpiryDay;
	}
	public Integer getGetExpiryDay(){
		return getExpiryDay;
	}
	public void setBonusExpiryDay(Integer bonusExpiryDay){
		this.bonusExpiryDay=bonusExpiryDay;
	}
	public Integer getBonusExpiryDay(){
		return bonusExpiryDay;
	}
	public void setRule(String rule){
		this.rule=rule;
	}
	public String getRule(){
		return rule;
	}
	public void setMessage(String message){
		this.message=message;
	}
	public String getMessage(){
		return message;
	}
	public void setCronExpression(String cronExpression){
		this.cronExpression=cronExpression;
	}
	public String getCronExpression(){
		return cronExpression;
	}
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
	public void setUpdateTime(Long updateTime){
		this.updateTime=updateTime;
	}
	public Long getUpdateTime(){
		return updateTime;
	}
	public void setSettle(Integer settle){
		this.settle=settle;
	}
	public Integer getSettle(){
		return settle;
	}
}

