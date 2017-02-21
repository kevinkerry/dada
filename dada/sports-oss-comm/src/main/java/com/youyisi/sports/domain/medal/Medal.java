package com.youyisi.sports.domain.medal;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-09-05
 */


public class Medal extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5087995784661150145L;
	private String name; //
	private String logo; //
	private Integer type; //
	private Integer category; //
	private String notLightLogo;
	private String note;

	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setLogo(String logo){
		this.logo=logo;
	}
	public String getLogo(){
		return logo;
	}
	public void setType(Integer type){
		this.type=type;
	}
	public Integer getType(){
		return type;
	}
	public void setCategory(Integer category){
		this.category=category;
	}
	public Integer getCategory(){
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
}

