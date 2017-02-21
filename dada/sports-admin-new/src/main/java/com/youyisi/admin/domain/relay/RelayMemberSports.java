package com.youyisi.admin.domain.relay;

import java.io.Serializable;

/**
 * 
 * @author hetao
 * @date 2016年9月21日 下午4:56:51
 * @version 1.0
 * 
 */
public class RelayMemberSports implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5373624335280496990L;

	// 步数
	private Integer step;
	// 步数创建时间
	private Long stepTime;
	// 室内跑步
	private Double distanceSN;
	// 室内跑步创建时间
	private Long distanceTimeSN;
	// 室外跑步
	private Double distanceSW;
	// 室外跑步创建时间
	private Long distanceTimeSW;
	// 总计
	private Double distanceSum;

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Long getStepTime() {
		return stepTime;
	}

	public void setStepTime(Long stepTime) {
		this.stepTime = stepTime;
	}

	public Double getDistanceSN() {
		return distanceSN;
	}

	public void setDistanceSN(Double distanceSN) {
		this.distanceSN = distanceSN;
	}

	public Long getDistanceTimeSN() {
		return distanceTimeSN;
	}

	public void setDistanceTimeSN(Long distanceTimeSN) {
		this.distanceTimeSN = distanceTimeSN;
	}

	public Double getDistanceSW() {
		return distanceSW;
	}

	public void setDistanceSW(Double distanceSW) {
		this.distanceSW = distanceSW;
	}

	public Long getDistanceTimeSW() {
		return distanceTimeSW;
	}

	public void setDistanceTimeSW(Long distanceTimeSW) {
		this.distanceTimeSW = distanceTimeSW;
	}

	public Double getDistanceSum() {
		return distanceSum;
	}

	public void setDistanceSum(Double distanceSum) {
		this.distanceSum = distanceSum;
	}

}
