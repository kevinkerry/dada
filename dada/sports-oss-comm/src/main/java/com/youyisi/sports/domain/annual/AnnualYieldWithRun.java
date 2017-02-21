package com.youyisi.sports.domain.annual;

import java.util.List;

import com.youyisi.sports.domain.BaseEntity;
import com.youyisi.sports.domain.distance.Distance;
import com.youyisi.sports.domain.run.Run;
import com.youyisi.sports.domain.step.Step;

/**
 * @author shuye
 * @time 2016-05-12
 */

public class AnnualYieldWithRun extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8168856507403330485L;
	private Long userId; //
	private Long date; //
	private Double annualYield; //
	private Double baseAnnualYield; //
	private Double activityAnnualYield;
	private Long createTime; //
	private Long updateTime; //
	private Step step;
	private List<Run> run;
	private Distance distance;
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Long getDate() {
		return date;
	}

	public void setAnnualYield(Double annualYield) {
		this.annualYield = annualYield;
	}

	public Double getAnnualYield() {
		return annualYield;
	}

	public void setBaseAnnualYield(Double baseAnnualYield) {
		this.baseAnnualYield = baseAnnualYield;
	}

	public Double getBaseAnnualYield() {
		return baseAnnualYield;
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

	@Override
	public String toString() {
		return "AnnualYield [userId=" + userId + ", date=" + date + ", annualYield=" + annualYield
				+ ", baseAnnualYield=" + baseAnnualYield + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	public List<Run> getRun() {
		return run;
	}

	public void setRun(List<Run> run) {
		this.run = run;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public Double getActivityAnnualYield() {
		return activityAnnualYield;
	}

	public void setActivityAnnualYield(Double activityAnnualYield) {
		this.activityAnnualYield = activityAnnualYield;
	}

}
