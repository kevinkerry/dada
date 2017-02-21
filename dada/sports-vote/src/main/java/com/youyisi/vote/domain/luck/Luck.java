package com.youyisi.vote.domain.luck;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2015-09-15
 */


public class Luck extends BaseObject<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -744434343385457989L;
	private String name; //
	private Integer num; //
	private Integer total; //
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setNum(Integer num){
		this.num=num;
	}
	public Integer getNum(){
		return num;
	}
	public void setTotal(Integer total){
		this.total=total;
	}
	public Integer getTotal(){
		return total;
	}
}

