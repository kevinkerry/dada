package com.youyisi.sports.domain.distance;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-05-12
 */

public class Distance extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5170012799065633778L;
	private Long userId; //
	private Long date; //
	private Double distance; //
	private Long createTime; //
	private Long updateTime; //


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

	@Override
	public String toString() {
		return "Distance [userId=" + userId + ", date=" + date + ", distance=" + distance + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

}
