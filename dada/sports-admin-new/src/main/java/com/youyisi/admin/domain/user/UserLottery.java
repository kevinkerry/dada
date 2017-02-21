package com.youyisi.admin.domain.user;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-10-22
 */

public class UserLottery extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3183117676144620256L;
	private Long userId; //
	private Long lotteryId; //
	private String myNumber; //
	private Long betCountId; //
	private Integer payStatus; //
	private Integer status; //
	private Long createTime; //

	/** 附加字段 中奖用户 **/
	private UserLotteryWin userLotteryWin;
	/** 附加字段 用户昵称 **/
	private String nickname;
	/** 附加字段 投注次数 **/
	private Integer betCount;

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

	public void setLotteryId(Long lotteryId) {
		this.lotteryId = lotteryId;
	}

	public Long getLotteryId() {
		return lotteryId;
	}

	public void setMyNumber(String myNumber) {
		this.myNumber = myNumber;
	}

	public String getMyNumber() {
		return myNumber;
	}

	public void setBetCountId(Long betCountId) {
		this.betCountId = betCountId;
	}

	public Long getBetCountId() {
		return betCountId;
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

	public UserLotteryWin getUserLotteryWin() {
		return userLotteryWin;
	}

	public void setUserLotteryWin(UserLotteryWin userLotteryWin) {
		this.userLotteryWin = userLotteryWin;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getBetCount() {
		return betCount;
	}

	public void setBetCount(Integer betCount) {
		this.betCount = betCount;
	}
}
