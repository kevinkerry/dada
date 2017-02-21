package com.youyisi.sports.domain.integralwall;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-08-09
 */


public class IntegralWall extends BaseEntity{
	private Long userId; //
	private String sn; //
	private String pk; //
	private Integer score; //
	private String sg; //
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setSn(String sn){
		this.sn=sn;
	}
	public String getSn(){
		return sn;
	}
	public void setPk(String pk){
		this.pk=pk;
	}
	public String getPk(){
		return pk;
	}
	public void setScore(Integer score){
		this.score=score;
	}
	public Integer getScore(){
		return score;
	}
	public void setSg(String sg){
		this.sg=sg;
	}
	public String getSg(){
		return sg;
	}
}

