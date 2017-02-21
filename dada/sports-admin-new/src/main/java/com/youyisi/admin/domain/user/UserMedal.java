package com.youyisi.admin.domain.user;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-09-08
 */

public class UserMedal extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3533131082092674176L;
	private Long medalId; //
	private Long userId; //
	private Long createTime; //

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setMedalId(Long medalId) {
		this.medalId = medalId;
	}

	public Long getMedalId() {
		return medalId;
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
}
