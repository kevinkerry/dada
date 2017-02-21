package com.youyisi.admin.domain.bet;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-10-21
 */

public class BetCount extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 468721982049256307L;
	private Integer count; //
	private Long lotteryId; //

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}

	public void setLotteryId(Long lotteryId) {
		this.lotteryId = lotteryId;
	}

	public Long getLotteryId() {
		return lotteryId;
	}
}
