package com.youyisi.admin.domain.user;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-09-22
 */

public class UserSnatch extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4349349070506404105L;
	private Long activityId; //
	private Long userId; //
	private Long snatchFeeId; //
	private Integer payStatus; //
	private Integer status; //
	private Long createTime; //

	/** 附加字段 用户 **/
	private User user;

	/** 附加字段 购买次数 **/
	private Integer count;
	/** 附加字段 支付金额 **/
	private Double money;
	/** 附加字段 步数 **/
	private Integer step;
	/** 附加字段 跑量 **/
	private Double distance;
	/** 附加字段 奖金数额 **/
	private Double bonus;
	/** 附加字段 收益核算 **/
	private Double earnings;
	/** 附加字段 使用状态 **/
	private Integer employ;

	/** 附加字段收入 **/
	private Double income;
	/** 附加字段 净利润 **/
	private Double profit;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setSnatchFeeId(Long snatchFeeId) {
		this.snatchFeeId = snatchFeeId;
	}

	public Long getSnatchFeeId() {
		return snatchFeeId;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getMoney() {
		return money;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Double getEarnings() {
		return earnings;
	}

	public void setEarnings(Double earnings) {
		this.earnings = earnings;
	}

	public Integer getEmploy() {
		return employ;
	}

	public void setEmploy(Integer employ) {
		this.employ = employ;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}
}
