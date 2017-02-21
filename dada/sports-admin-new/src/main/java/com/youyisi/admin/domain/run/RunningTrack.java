package com.youyisi.admin.domain.run;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-06-27
 */

public class RunningTrack extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1170662723545672711L;
	private Long runId; //
	private Long userId; //
	private Double lat; //
	private Double lon; //
	private Double distance; //
	private Double avspeed; //
	private Integer type; //
	private Long createTime; //

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

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLat() {
		return lat;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLon() {
		return lon;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getDistance() {
		return distance;
	}

	public void setAvspeed(Double avspeed) {
		this.avspeed = avspeed;
	}

	public Double getAvspeed() {
		return avspeed;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}
}
