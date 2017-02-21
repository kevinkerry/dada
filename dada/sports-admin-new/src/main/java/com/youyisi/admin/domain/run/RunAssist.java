package com.youyisi.admin.domain.run;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-08-25
 */

public class RunAssist extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6462667987242878387L;
	private Long runId; //
	private Long createTime; //
	private Double avspeed; //
	private Double distance; //

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	public Long getRunId() {
		return runId;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setAvspeed(Double avspeed) {
		this.avspeed = avspeed;
	}

	public Double getAvspeed() {
		return avspeed;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getDistance() {
		return distance;
	}
}
