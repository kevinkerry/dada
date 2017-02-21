package com.youyisi.admin.domain.lottery;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-10-21
 */

public class LotteryWin extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7049739080489170795L;
	private Long lotteryId; //
	private Integer level; //
	private Double goldBean; //

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setLotteryId(Long lotteryId) {
		this.lotteryId = lotteryId;
	}

	public Long getLotteryId() {
		return lotteryId;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getLevel() {
		return level;
	}

	public void setGoldBean(Double goldBean) {
		this.goldBean = goldBean;
	}

	public Double getGoldBean() {
		return goldBean;
	}
}
