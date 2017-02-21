package com.youyisi.sports.gexinpush;

import java.util.Map;

import com.youyisi.sports.utils.DateUtil;

public class TransmissionContent {
	private Long toUserId;
	private String type;
	private String title;
	private Map<String, Object> entity;
	private Long time = DateUtil.getDate().getTime();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Map<String, Object> getEntity() {
		return entity;
	}

	public void setEntity(Map<String, Object> entity) {
		this.entity = entity;
	}

	public Long getTime() {
		return DateUtil.getDate().getTime();
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

}
