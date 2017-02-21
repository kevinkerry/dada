package com.youyisi.admin.domain.lottery;

import java.util.ArrayList;
import java.util.List;

import com.youyisi.admin.domain.bet.BetCountBase;
import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-10-21
 */

public class LotteryBase extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6194688250162824624L;
	private String name; //
	private String logo; //
	private Double goldBean; //
	private Long beginTime; //
	private Long endTime; //
	private Integer getExpiryDay; //
	private Integer bonusExpiryDay; //
	private String rule; //
	private String message; //
	private String cronExpression; //
	private Long createTime; //
	private Long updateTime; //

	/** 附加字段 投注次数 **/
	private List<BetCountBase> betCountBaseList = new ArrayList<BetCountBase>();

	/** 附加字段 奖金等级 **/
	private List<LotteryWinBase> lotteryWinBaseList = new ArrayList<LotteryWinBase>();

	/** 附加字段 延期开奖 **/
	private List<DelayLotteryBase> delayLotteryBaseList = new ArrayList<DelayLotteryBase>();

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogo() {
		return logo;
	}

	public void setGoldBean(Double goldBean) {
		this.goldBean = goldBean;
	}

	public Double getGoldBean() {
		return goldBean;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}

	public Long getBeginTime() {
		return beginTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setGetExpiryDay(Integer getExpiryDay) {
		this.getExpiryDay = getExpiryDay;
	}

	public Integer getGetExpiryDay() {
		return getExpiryDay;
	}

	public void setBonusExpiryDay(Integer bonusExpiryDay) {
		this.bonusExpiryDay = bonusExpiryDay;
	}

	public Integer getBonusExpiryDay() {
		return bonusExpiryDay;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getRule() {
		return rule;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public List<BetCountBase> getBetCountBaseList() {
		return betCountBaseList;
	}

	public void setBetCountBaseList(List<BetCountBase> betCountBaseList) {
		this.betCountBaseList = betCountBaseList;
	}

	public List<LotteryWinBase> getLotteryWinBaseList() {
		return lotteryWinBaseList;
	}

	public void setLotteryWinBaseList(List<LotteryWinBase> lotteryWinBaseList) {
		this.lotteryWinBaseList = lotteryWinBaseList;
	}

	public List<DelayLotteryBase> getDelayLotteryBaseList() {
		return delayLotteryBaseList;
	}

	public void setDelayLotteryBaseList(List<DelayLotteryBase> delayLotteryBaseList) {
		this.delayLotteryBaseList = delayLotteryBaseList;
	}

	@Override
	public String toString() {
		return "LotteryBase [name=" + name + ", logo=" + logo + ", goldBean=" + goldBean + ", beginTime=" + beginTime
				+ ", endTime=" + endTime + ", getExpiryDay=" + getExpiryDay + ", bonusExpiryDay=" + bonusExpiryDay
				+ ", rule=" + rule + ", message=" + message + ", cronExpression=" + cronExpression + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", betCountBaseList=" + betCountBaseList
				+ ", lotteryWinBaseList=" + lotteryWinBaseList + ", delayLotteryBaseList=" + delayLotteryBaseList + "]";
	}

}
