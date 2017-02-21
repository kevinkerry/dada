package com.youyisi.admin.domain.orders;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-06-20
 */

public class OrderFlow extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7469115043644256854L;
	private Long orderId; //
	private Integer payType; //
	private String tradeNo; //
	private Long userId; //
	private String note; //
	private Long createTime; //

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

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
