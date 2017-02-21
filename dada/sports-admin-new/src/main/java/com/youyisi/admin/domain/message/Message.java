package com.youyisi.admin.domain.message;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-05-24
 */

public class Message extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8261662973696700471L;
	private Long adminId; //
	private String title; //
	private String content; //
	private Long createTime; //
	private Long sendTime; //
	private Integer type; //
	private Integer status; //

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setSendTime(Long sendTime) {
		this.sendTime = sendTime;
	}

	public Long getSendTime() {
		return sendTime;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
