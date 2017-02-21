package com.youyisi.sports.domain.run;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-05-12
 */

public class Run extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1012379358924822481L;
	private Long userId; //
	private Long createTime; //
	private Integer type; //
	private Long date; //
	private Double distance; //
	private Double avspeed; //
	private Long totalTime;
	private Integer step;
	private Double realDistance; //
	private Double maxspeed; //
	private Double minspeed; //
	private Integer status; //
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getAvspeed() {
		return avspeed;
	}

	public void setAvspeed(Double avspeed) {
		this.avspeed = avspeed;
	}

	public Long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Double getRealDistance() {
		return realDistance;
	}

	public void setRealDistance(Double realDistance) {
		this.realDistance = realDistance;
	}

	public Double getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(Double maxspeed) {
		this.maxspeed = maxspeed;
	}

	public Double getMinspeed() {
		return minspeed;
	}

	public void setMinspeed(Double minspeed) {
		this.minspeed = minspeed;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
