package com.youyisi.admin.domain.lottery;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-10-24
 */

public class DelayLotteryBase extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3737991013908285198L;
	private String lotteryNum; //
	private Long lotteryTime; //

	/** 附加字段 开奖日期 **/
	private String lotteryTimeStr; //

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setLotteryNum(String lotteryNum) {
		this.lotteryNum = lotteryNum;
	}

	public String getLotteryNum() {
		return lotteryNum;
	}

	public void setLotteryTime(Long lotteryTime) {
		this.lotteryTime = lotteryTime;
	}

	public Long getLotteryTime() {
		return lotteryTime;
	}

	public String getLotteryTimeStr() {
		return lotteryTimeStr;
	}

	public void setLotteryTimeStr(String lotteryTimeStr) {
		this.lotteryTimeStr = lotteryTimeStr;
	}
}
