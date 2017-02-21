package com.youyisi.sports.domain.orders;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-05-12
 */

public class OrderFlow extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5237347532052589414L;
	private Long orderId; //
	private Integer payType; //
	private String tradeNo; //
	private Long userId; //
	private String note; //
	private Long createTime; //


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}
}
