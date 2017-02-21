package com.youyisi.admin.domain.feedback;

import com.youyisi.admin.domain.user.User;
import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-05-23
 */

public class Feedback extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7152633993304878821L;
	private Long userId; //
	private String content; //
	private String contact; //
	private Long createTime; //
	private Integer status; //

	/** 附加字段 用户 **/
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

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContact() {
		return contact;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
