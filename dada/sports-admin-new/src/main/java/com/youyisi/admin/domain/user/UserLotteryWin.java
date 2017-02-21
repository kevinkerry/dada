package com.youyisi.admin.domain.user;

import com.youyisi.admin.domain.lottery.LotteryWin;
import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-10-24
 */

public class UserLotteryWin extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2917996683353368138L;
	private Long userId; //
	private Long lotteryId; //
	private Long lotteryWinId; //
	private Long expiryTime; //
	private Long createTime; //
	private Integer status; //
	private Long updateTime; //

	private User user;

	private LotteryWin lotteryWin;

	private UserCoupon userCoupon;

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

	public void setLotteryWinId(Long lotteryWinId) {
		this.lotteryWinId = lotteryWinId;
	}

	public Long getLotteryWinId() {
		return lotteryWinId;
	}

	public void setExpiryTime(Long expiryTime) {
		this.expiryTime = expiryTime;
	}

	public Long getExpiryTime() {
		return expiryTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LotteryWin getLotteryWin() {
		return lotteryWin;
	}

	public void setLotteryWin(LotteryWin lotteryWin) {
		this.lotteryWin = lotteryWin;
	}

	public UserCoupon getUserCoupon() {
		return userCoupon;
	}

	public void setUserCoupon(UserCoupon userCoupon) {
		this.userCoupon = userCoupon;
	}
}
