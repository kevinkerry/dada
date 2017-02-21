package com.youyisi.admin.domain.lottery;

import java.util.List;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-10-21
 */

public class Lottery extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8690021990752072490L;
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

	/** 附加字段 中奖 **/
	private List<LotteryWin> lotteryWinList;
	/** 附加字段 总投注 **/
	private Integer sumCount;
	/** 附加字段 一等奖数量 **/
	private Integer level1 = 0;
	/** 附加字段 二等奖数量 **/
	private Integer level2 = 0;
	/** 附加字段 三等奖数量 **/
	private Integer level3 = 0;
	/** 附加字段 四等奖数量 **/
	private Integer level4 = 0;
	/** 附加字段 五等奖数量 **/
	private Integer level5 = 0;
	/** 附加字段 总投注金额 **/
	private Double countGoldBean;
	/** 附加字段 总奖金 **/
	private Double sumBonus;

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

	public void setWinNumber(String winNumber) {
		this.winNumber = winNumber;
	}

	public String getWinNumber() {
		return winNumber;
	}

	public void setLotteryNum(String lotteryNum) {
		this.lotteryNum = lotteryNum;
	}

	public String getLotteryNum() {
		return lotteryNum;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Long getDate() {
		return date;
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

	public void setLotteryTime(Long lotteryTime) {
		this.lotteryTime = lotteryTime;
	}

	public Long getLotteryTime() {
		return lotteryTime;
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

	public List<LotteryWin> getLotteryWinList() {
		return lotteryWinList;
	}

	public void setLotteryWinList(List<LotteryWin> lotteryWinList) {
		this.lotteryWinList = lotteryWinList;
	}

	public Integer getSettle() {
		return settle;
	}

	public void setSettle(Integer settle) {
		this.settle = settle;
	}

	public Integer getSumCount() {
		return sumCount;
	}

	public void setSumCount(Integer sumCount) {
		this.sumCount = sumCount;
	}

	public Integer getLevel1() {
		return level1;
	}

	public void setLevel1(Integer level1) {
		this.level1 = level1;
	}

	public Integer getLevel2() {
		return level2;
	}

	public void setLevel2(Integer level2) {
		this.level2 = level2;
	}

	public Integer getLevel3() {
		return level3;
	}

	public void setLevel3(Integer level3) {
		this.level3 = level3;
	}

	public Integer getLevel4() {
		return level4;
	}

	public void setLevel4(Integer level4) {
		this.level4 = level4;
	}

	public Integer getLevel5() {
		return level5;
	}

	public void setLevel5(Integer level5) {
		this.level5 = level5;
	}

	public Double getCountGoldBean() {
		return countGoldBean;
	}

	public void setCountGoldBean(Double countGoldBean) {
		this.countGoldBean = countGoldBean;
	}

	public Double getSumBonus() {
		return sumBonus;
	}

	public void setSumBonus(Double sumBonus) {
		this.sumBonus = sumBonus;
	}
}
