package com.youyisi.vote.domain.spot;

import com.youyisi.lang.domain.BaseObject;

/**
 * user 实体类 Thu Nov 13 17:51:23 CST 2014 shuye
 */

public class Spot extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6776455768797659474L;
	private String name;
	private String mobile;
	private Long createTime;
	private String spotA;
	private String spotB;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getSpotA() {
		return spotA;
	}
	public void setSpotA(String spotA) {
		this.spotA = spotA;
	}
	public String getSpotB() {
		return spotB;
	}
	public void setSpotB(String spotB) {
		this.spotB = spotB;
	}
}
