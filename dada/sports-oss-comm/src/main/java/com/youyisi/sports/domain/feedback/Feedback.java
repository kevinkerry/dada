package com.youyisi.sports.domain.feedback;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-05-13
 */


public class Feedback extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3984265462776292900L;
	private Long userId; //
	private String content; //
	private String contact; //
	private Long createTime; //
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	public void setContact(String contact){
		this.contact=contact;
	}
	public String getContact(){
		return contact;
	}
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
}

