package com.youyisi.admin.domain.relay;

import com.youyisi.admin.domain.user.User;
import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-10-10
 */

public class RelayMessage extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1294745266341063197L;
	private Long userId; //
	private Long teamId; //
	private String message; //
	private Long createTime; //

	private User user;

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

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
