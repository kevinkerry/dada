package com.youyisi.admin.domain.relay;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-09-06
 */

public class RelayRaceActivity extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5019773969876016411L;
	private Long activityId; //
	private Integer teamLimit; //
	private Integer relayBatonLimit; //
	private Double firstFee; //
	private Double otherFee; //
	private Integer levelLimit; //
	private Double firstBonus; //
	private Double otherBonus; //
	private Integer inviteExpiryDay; //
	private Integer leagueBonusExpiryDay; //
	private Double contributeBonus; //
	private Integer stepToDistance; //
	private Long maxStep; //
	private Double maxDistance; //
	private Long activityMedal; //
	private Long leagueMedal; //
	private Long teamLeaderMedal; //
	private Integer pushCount;
	private String rule; //
	private String message; //
	private String cronExpression; //
	private Long createTime; //
	private Long updateTime; //

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

	public void setTeamLimit(Integer teamLimit) {
		this.teamLimit = teamLimit;
	}

	public Integer getTeamLimit() {
		return teamLimit;
	}

	public void setRelayBatonLimit(Integer relayBatonLimit) {
		this.relayBatonLimit = relayBatonLimit;
	}

	public Integer getRelayBatonLimit() {
		return relayBatonLimit;
	}

	public void setFirstFee(Double firstFee) {
		this.firstFee = firstFee;
	}

	public Double getFirstFee() {
		return firstFee;
	}

	public void setOtherFee(Double otherFee) {
		this.otherFee = otherFee;
	}

	public Double getOtherFee() {
		return otherFee;
	}

	public void setLevelLimit(Integer levelLimit) {
		this.levelLimit = levelLimit;
	}

	public Integer getLevelLimit() {
		return levelLimit;
	}

	public void setFirstBonus(Double firstBonus) {
		this.firstBonus = firstBonus;
	}

	public Double getFirstBonus() {
		return firstBonus;
	}

	public void setOtherBonus(Double otherBonus) {
		this.otherBonus = otherBonus;
	}

	public Double getOtherBonus() {
		return otherBonus;
	}

	public void setInviteExpiryDay(Integer inviteExpiryDay) {
		this.inviteExpiryDay = inviteExpiryDay;
	}

	public Integer getInviteExpiryDay() {
		return inviteExpiryDay;
	}

	public void setLeagueBonusExpiryDay(Integer leagueBonusExpiryDay) {
		this.leagueBonusExpiryDay = leagueBonusExpiryDay;
	}

	public Integer getLeagueBonusExpiryDay() {
		return leagueBonusExpiryDay;
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

	public void setLeagueMedal(Long leagueMedal) {
		this.leagueMedal = leagueMedal;
	}

	public Long getLeagueMedal() {
		return leagueMedal;
	}

	public void setTeamLeaderMedal(Long teamLeaderMedal) {
		this.teamLeaderMedal = teamLeaderMedal;
	}

	public Long getTeamLeaderMedal() {
		return teamLeaderMedal;
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

	public Integer getPushCount() {
		return pushCount;
	}

	public void setPushCount(Integer pushCount) {
		this.pushCount = pushCount;
	}
}
