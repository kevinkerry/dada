package com.youyisi.sports.domain.area;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-05-30
 */


public class Area extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2757045923572290063L;
	private String name; //
	private Long parentId; //
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setParentId(Long parentId){
		this.parentId=parentId;
	}
	public Long getParentId(){
		return parentId;
	}
}

