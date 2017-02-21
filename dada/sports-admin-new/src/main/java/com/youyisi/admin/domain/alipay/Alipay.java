package com.youyisi.admin.domain.alipay;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-05-21
 */

public class Alipay extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7355556744313721091L;
	private Long userId; //
	private String alipay; //
	private String realName; //
	private Long createTime; //

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

	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}

	public String getAlipay() {
		return alipay;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRealName() {
		return realName;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}
}
