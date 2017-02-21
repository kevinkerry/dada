package com.youyisi.admin.domain.relay;

import com.youyisi.admin.domain.user.User;
import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-09-06
 */

public class RelayMember extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1851097334236551587L;
	private Long activityId; //
	private Long userId; //
	private Long teamId; //
	private Long parentId; //
	private Integer payStatus; //
	private Integer status; //
	private Long createTime; //
	private Integer level; //
	private String usercode; //

	/** 附加字段 累计获得体验金 **/
	private Double countBonus;
	/** 附加字段 冠军奖金 **/
	private Double bonusFirst;
	/** 附加字段 未使用体验金 **/
	private Double unusedMoney;
	/** 附加字段 战队名称 **/
	private String teamName;

	/** 附加字段 用户 **/
	private User user;

	/** 附加字段 上线人数 **/
	private Integer topThreadNum;
	/** 附加字段 一级下线 **/
	private Integer stairTapeout;
	/** 附加字段 其他下线 **/
	private Integer otherTapeout;

	/** 附加字段 点赞 **/
	private Integer praise;
	/** 附加字段 激励 **/
	private Integer stimulate;

	/** 附加字段 累计收益 **/
	private Double earnings;

	/** 用户运动数据 **/
	private RelayMemberSports relayMemberSports;

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

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
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

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getLevel() {
		return level;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUsercode() {
		return usercode;
	}

	public Double getCountBonus() {
		return countBonus;
	}

	public void setCountBonus(Double countBonus) {
		this.countBonus = countBonus;
	}

	public Double getBonusFirst() {
		return bonusFirst;
	}

	public void setBonusFirst(Double bonusFirst) {
		this.bonusFirst = bonusFirst;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getUnusedMoney() {
		return unusedMoney;
	}

	public void setUnusedMoney(Double unusedMoney) {
		this.unusedMoney = unusedMoney;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getTopThreadNum() {
		return topThreadNum;
	}

	public void setTopThreadNum(Integer topThreadNum) {
		this.topThreadNum = topThreadNum;
	}

	public Integer getStairTapeout() {
		return stairTapeout;
	}

	public void setStairTapeout(Integer stairTapeout) {
		this.stairTapeout = stairTapeout;
	}

	public Integer getOtherTapeout() {
		return otherTapeout;
	}

	public void setOtherTapeout(Integer otherTapeout) {
		this.otherTapeout = otherTapeout;
	}

	public Double getEarnings() {
		return earnings;
	}

	public void setEarnings(Double earnings) {
		this.earnings = earnings;
	}

	public RelayMemberSports getRelayMemberSports() {
		return relayMemberSports;
	}

	public void setRelayMemberSports(RelayMemberSports relayMemberSports) {
		this.relayMemberSports = relayMemberSports;
	}

	public Integer getPraise() {
		return praise;
	}

	public void setPraise(Integer praise) {
		this.praise = praise;
	}

	public Integer getStimulate() {
		return stimulate;
	}

	public void setStimulate(Integer stimulate) {
		this.stimulate = stimulate;
	}
}
