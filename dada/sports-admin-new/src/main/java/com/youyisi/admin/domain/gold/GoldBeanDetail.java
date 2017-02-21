package com.youyisi.admin.domain.gold;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-10-21
 */

public class GoldBeanDetail extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6586718255504809595L;
	private Long userId; //
	private Integer type; //
	private Integer category; //
	private Double goldBean; //
	private Long date; //
	private Double result; //
	private Long createTime; //
	private String note; //
	private Long productId; //

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

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getCategory() {
		return category;
	}

	public void setGoldBean(Double goldBean) {
		this.goldBean = goldBean;
	}

	public Double getGoldBean() {
		return goldBean;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Long getDate() {
		return date;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public Double getResult() {
		return result;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getProductId() {
		return productId;
	}
}
