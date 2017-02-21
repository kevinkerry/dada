package com.youyisi.admin.domain.sportrecord;

import java.io.Serializable;

/**
 * 
 * @author hetao
 * @date 2016年6月27日 上午9:36:55
 * @version 1.0
 * @parameter
 * @return
 */
public class SportRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 56197638864632684L;

	/** 今日计步 **/
	private Integer todayStep;

	/** 今日跑量 **/
	private Double todayDistance; //

	/** 今日累计跑步时长 **/
	private String todayDistanceTime;

	/** 累计计步 **/
	private Integer countStep;

	/** 累计跑步 **/
	private Double countDistance; //

	/** 累计跑步时长 **/
	private String countDistanceTime;

	/** 计步 5000以下 **/
	private Integer step5000DN;

	/** 计步 5000-9999 **/
	private Integer step5000_9999;

	/** 计步 10000-14999 **/
	private Integer step10000_14999;

	/** 计步 15000_19999 **/
	private Integer step15000_19999;

	/** 计步 20000+ **/
	private Integer step20000UP;

	/** 跑步0_3 **/
	private Integer distance0_3KM;

	/** 跑步3_5 **/
	private Integer distance3_5KM;

	/** 跑步5_8 **/
	private Integer distance5_8KM;

	/** 跑步8_10 **/
	private Integer distance8_10KM;

	/** 跑步10KMUP **/
	private Integer distance10KMUP;

	/** 室内跑步 **/
	private Integer snCountRunning;

	/** 室外跑步 **/
	private Integer swCountRunning;

	public Integer getTodayStep() {
		return todayStep;
	}

	public void setTodayStep(Integer todayStep) {
		this.todayStep = todayStep;
	}

	public Double getTodayDistance() {
		return todayDistance;
	}

	public void setTodayDistance(Double todayDistance) {
		this.todayDistance = todayDistance;
	}

	public String getTodayDistanceTime() {
		return todayDistanceTime;
	}

	public void setTodayDistanceTime(String todayDistanceTime) {
		this.todayDistanceTime = todayDistanceTime;
	}

	public Integer getCountStep() {
		return countStep;
	}

	public void setCountStep(Integer countStep) {
		this.countStep = countStep;
	}

	public Double getCountDistance() {
		return countDistance;
	}

	public void setCountDistance(Double countDistance) {
		this.countDistance = countDistance;
	}

	public String getCountDistanceTime() {
		return countDistanceTime;
	}

	public void setCountDistanceTime(String countDistanceTime) {
		this.countDistanceTime = countDistanceTime;
	}

	public Integer getSnCountRunning() {
		return snCountRunning;
	}

	public void setSnCountRunning(Integer snCountRunning) {
		this.snCountRunning = snCountRunning;
	}

	public Integer getSwCountRunning() {
		return swCountRunning;
	}

	public void setSwCountRunning(Integer swCountRunning) {
		this.swCountRunning = swCountRunning;
	}

	public Integer getStep5000DN() {
		return step5000DN;
	}

	public void setStep5000DN(Integer step5000dn) {
		step5000DN = step5000dn;
	}

	public Integer getStep5000_9999() {
		return step5000_9999;
	}

	public void setStep5000_9999(Integer step5000_9999) {
		this.step5000_9999 = step5000_9999;
	}

	public Integer getStep10000_14999() {
		return step10000_14999;
	}

	public void setStep10000_14999(Integer step10000_14999) {
		this.step10000_14999 = step10000_14999;
	}

	public Integer getStep15000_19999() {
		return step15000_19999;
	}

	public void setStep15000_19999(Integer step15000_19999) {
		this.step15000_19999 = step15000_19999;
	}

	public Integer getStep20000UP() {
		return step20000UP;
	}

	public void setStep20000UP(Integer step20000up) {
		step20000UP = step20000up;
	}

	public Integer getDistance0_3KM() {
		return distance0_3KM;
	}

	public void setDistance0_3KM(Integer distance0_3km) {
		distance0_3KM = distance0_3km;
	}

	public Integer getDistance3_5KM() {
		return distance3_5KM;
	}

	public void setDistance3_5KM(Integer distance3_5km) {
		distance3_5KM = distance3_5km;
	}

	public Integer getDistance5_8KM() {
		return distance5_8KM;
	}

	public void setDistance5_8KM(Integer distance5_8km) {
		distance5_8KM = distance5_8km;
	}

	public Integer getDistance8_10KM() {
		return distance8_10KM;
	}

	public void setDistance8_10KM(Integer distance8_10km) {
		distance8_10KM = distance8_10km;
	}

	public Integer getDistance10KMUP() {
		return distance10KMUP;
	}

	public void setDistance10KMUP(Integer distance10kmup) {
		distance10KMUP = distance10kmup;
	}
}
