package com.youyisi.admin.domain.gold;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-10-21
 */

public class GoldBean extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7736824790934023796L;
	private Long userId; //
	private Double goldBean; //

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

	public void setGoldBean(Double goldBean) {
		this.goldBean = goldBean;
	}

	public Double getGoldBean() {
		return goldBean;
	}
}
