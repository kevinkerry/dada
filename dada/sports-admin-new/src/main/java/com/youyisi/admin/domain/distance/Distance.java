package com.youyisi.admin.domain.distance;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-06-02
 */

public class Distance extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8805891698721753064L;
	private Long userId; //
	private Long date; //
	private Double distance; //
	private Long createTime; //
	private Long updateTime; //

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

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

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getDistance() {
		return distance;
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
}
