package com.youyisi.admin.domain.run;

import com.youyisi.admin.domain.user.User;
import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-06-27
 */

public class Run extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -92441682832795613L;
	private Long userId; //
	private Long createTime; //
	private Integer type; //
	private Long date; //
	private Double distance; //
	private Double avspeed; //
	private Long totalTime; //
	private Integer step; //
	private Double realDistance; //
	private Double maxspeed;
	private Double minspeed;

	private User user;

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

	public void setAvspeed(Double avspeed) {
		this.avspeed = avspeed;
	}

	public Double getAvspeed() {
		return avspeed;
	}

	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}

	public Long getTotalTime() {
		return totalTime;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Integer getStep() {
		return step;
	}

	public void setRealDistance(Double realDistance) {
		this.realDistance = realDistance;
	}

	public Double getRealDistance() {
		return realDistance;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Run [userId=" + userId + ", createTime=" + createTime + ", type=" + type + ", date=" + date
				+ ", distance=" + distance + ", avspeed=" + avspeed + ", totalTime=" + totalTime + ", step=" + step
				+ ", realDistance=" + realDistance + ", maxspeed=" + maxspeed + ", minspeed=" + minspeed + ", user="
				+ user + ", id=" + id + "]";
	}

}
