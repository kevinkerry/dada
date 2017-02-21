package com.youyisi.admin.domain.bet;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-10-21
 */

public class BetCountBase extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2787530471840868019L;
	private Integer count; //
	private Long lotteryBaseId; //

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

	public void setLotteryBaseId(Long lotteryBaseId) {
		this.lotteryBaseId = lotteryBaseId;
	}

	public Long getLotteryBaseId() {
		return lotteryBaseId;
	}
}
