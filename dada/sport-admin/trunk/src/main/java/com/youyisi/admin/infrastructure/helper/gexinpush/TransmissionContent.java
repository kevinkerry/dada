package com.youyisi.admin.infrastructure.helper.gexinpush;

import java.util.Date;
import java.util.Map;

public class TransmissionContent {
	private String type;
	private String title;
	private Map<String,Object> entity;
	private Date time;
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
	public Date getTime() {
		return new Date();
	}
}

