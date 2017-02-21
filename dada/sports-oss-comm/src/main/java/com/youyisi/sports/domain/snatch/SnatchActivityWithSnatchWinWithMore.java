package com.youyisi.sports.domain.snatch;

import java.util.List;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-09-21
 */


public class SnatchActivityWithSnatchWinWithMore extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4203619424731232785L;
	private String activityNum; //
	private Long activityId; //
	private Integer minNum; //
	private Double contributeBonus; //
	private Integer stepToDistance; //
	private Long maxStep; //
	private Double maxDistance; //
	private Long createTime; //
	private Long updateTime; //
	private Integer settle;
	private List<SnatchWinWithMore> snatchWinWithMore;
	
	public void setActivityNum(String activityNum){
		this.activityNum=activityNum;
	}
	public String getActivityNum(){
		return activityNum;
	}
	public void setActivityId(Long activityId){
		this.activityId=activityId;
	}
	public Long getActivityId(){
		return activityId;
	}
	
	public Integer getStepToDistance() {
		return stepToDistance;
	}
	public void setStepToDistance(Integer stepToDistance) {
		this.stepToDistance = stepToDistance;
	}
	public Long getMaxStep() {
		return maxStep;
	}
	public void setMaxStep(Long maxStep) {
		this.maxStep = maxStep;
	}
	public Double getMaxDistance() {
		return maxDistance;
	}
	public void setMaxDistance(Double maxDistance) {
		this.maxDistance = maxDistance;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	public List<SnatchWinWithMore> getSnatchWinWithMore() {
		return snatchWinWithMore;
	}
	public void setSnatchWinWithMore(List<SnatchWinWithMore> snatchWinWithMore) {
		this.snatchWinWithMore = snatchWinWithMore;
	}
	public Integer getMinNum() {
		return minNum;
	}
	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}
	public Double getContributeBonus() {
		return contributeBonus;
	}
	public void setContributeBonus(Double contributeBonus) {
		this.contributeBonus = contributeBonus;
	}
	public Integer getSettle() {
		return settle;
	}
	public void setSettle(Integer settle) {
		this.settle = settle;
	}
	
}

