package com.youyisi.admin.domain.relay;

import com.youyisi.admin.domain.user.User;
import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-09-06
 */

public class RelayTeam extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5032267174772484781L;
	private Long activityId; //
	private String name; //
	private String logo; //
	private Long userId; //
	private Integer status; //
	private Long createTime; //
	private Long updateTime; //
	private Integer settle;

	/** 附加字段 用户 **/
	private User user;
	/** 附加字段 接力活动 **/
	private RelayRaceActivity relayRaceActivity;

	/** 附加字段 战队下面的人数 **/
	private Integer num;

	private Double sumDistance;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogo() {
		return logo;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getSumDistance() {
		return sumDistance;
	}

	public void setSumDistance(Double sumDistance) {
		this.sumDistance = sumDistance;
	}

	public RelayRaceActivity getRelayRaceActivity() {
		return relayRaceActivity;
	}

	public void setRelayRaceActivity(RelayRaceActivity relayRaceActivity) {
		this.relayRaceActivity = relayRaceActivity;
	}

	public Integer getSettle() {
		return settle;
	}

	public void setSettle(Integer settle) {
		this.settle = settle;
	}
}
