package com.youyisi.admin.domain.snatch;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-09-22
 */

public class SnatchActivity extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9035386245991840958L;
	private String activityNum; //
	private Long activityId; //
	private Double initialBonus; //
	private Integer minNum; //
	private Integer winNum; //
	private Integer expiryDay; //
	private Double contributeBonus; //
	private Integer stepToDistance; //
	private Long maxStep; //
	private Double maxDistance; //
	private Long activityMedal; //
	private Long winMedal; //
	private String rule; //
	private String message; //
	private String cronExpression; //
	private Long createTime; //
	private Long updateTime; //
	private Integer settle; //

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setActivityNum(String activityNum) {
		this.activityNum = activityNum;
	}

	public String getActivityNum() {
		return activityNum;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setInitialBonus(Double initialBonus) {
		this.initialBonus = initialBonus;
	}

	public Double getInitialBonus() {
		return initialBonus;
	}

	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}

	public Integer getMinNum() {
		return minNum;
	}

	public void setExpiryDay(Integer expiryDay) {
		this.expiryDay = expiryDay;
	}

	public Integer getExpiryDay() {
		return expiryDay;
	}

	public void setContributeBonus(Double contributeBonus) {
		this.contributeBonus = contributeBonus;
	}

	public Double getContributeBonus() {
		return contributeBonus;
	}

	public void setStepToDistance(Integer stepToDistance) {
		this.stepToDistance = stepToDistance;
	}

	public Integer getStepToDistance() {
		return stepToDistance;
	}

	public void setMaxStep(Long maxStep) {
		this.maxStep = maxStep;
	}

	public Long getMaxStep() {
		return maxStep;
	}

	public void setMaxDistance(Double maxDistance) {
		this.maxDistance = maxDistance;
	}

	public Double getMaxDistance() {
		return maxDistance;
	}

	public void setActivityMedal(Long activityMedal) {
		this.activityMedal = activityMedal;
	}

	public Long getActivityMedal() {
		return activityMedal;
	}

	public void setWinMedal(Long winMedal) {
		this.winMedal = winMedal;
	}

	public Long getWinMedal() {
		return winMedal;
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

	public void setSettle(Integer settle) {
		this.settle = settle;
	}

	public Integer getSettle() {
		return settle;
	}

	public Integer getWinNum() {
		return winNum;
	}

	public void setWinNum(Integer winNum) {
		this.winNum = winNum;
	}
}
