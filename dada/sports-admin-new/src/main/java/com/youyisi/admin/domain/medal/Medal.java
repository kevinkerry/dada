package com.youyisi.admin.domain.medal;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-09-07
 */

public class Medal extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2484244914933039970L;
	private String name; //
	private String logo; //
	private Integer type; //
	private Integer category; //
	private String notLightLogo;
	private String note;

	/** 关联的活动 **/
	private String correlationActivity;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogo() {
		return logo;
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

	public String getNotLightLogo() {
		return notLightLogo;
	}

	public void setNotLightLogo(String notLightLogo) {
		this.notLightLogo = notLightLogo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCorrelationActivity() {
		return correlationActivity;
	}

	public void setCorrelationActivity(String correlationActivity) {
		this.correlationActivity = correlationActivity;
	}
}
