package com.youyisi.admin.domain.lottery;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-10-21
 */

public class LotteryWinBase extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8500513999681819477L;
	private Long lotteryBaseId; //
	private Integer level; //
	private Double goldBean; //

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setLotteryBaseId(Long lotteryBaseId) {
		this.lotteryBaseId = lotteryBaseId;
	}

	public Long getLotteryBaseId() {
		return lotteryBaseId;
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
