package com.youyisi.admin.domain.orders;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-06-20
 */

public class Orders extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7715474977560762845L;
	private String orderNumber; //
	private Integer type; //
	private Long productId; //
	private Double orderAmount; //
	private Double payAmount; //
	private Integer counts; //
	private Integer payStatus; //
	private Integer orderStatus; //
	private String note; //
	private Long userId; //
	private Long createTime; //
	private Long updateTime; //

	/** 订单流水 **/
	private OrderFlow orderFlow;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public Double getPayAmount() {
		return payAmount;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public OrderFlow getOrderFlow() {
		return orderFlow;
	}

	public void setOrderFlow(OrderFlow orderFlow) {
		this.orderFlow = orderFlow;
	}
}
