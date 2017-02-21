package com.youyisi.admin.domain.lottery;

import java.io.Serializable;

public class LotteryTemplate implements Serializable {

	private static final long serialVersionUID = 3735742688053395161L;

	private Long lotteryId;
	private String winNumber;
	private String content;
	private String pushTime;

	public Long getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(Long lotteryId) {
		this.lotteryId = lotteryId;
	}

	public String getWinNumber() {
		return winNumber;
	}

	public void setWinNumber(String winNumber) {
		this.winNumber = winNumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPushTime() {
		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}

}
