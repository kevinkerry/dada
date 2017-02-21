package com.youyisi.vote.domain.user;

import com.youyisi.lang.domain.BaseObject;

/**
 * user 实体类 Thu Nov 13 17:51:23 CST 2014 shuye
 */

public class LeadUser extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4451356397576364485L;
	private Long createTime;
	private Long userId;

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
