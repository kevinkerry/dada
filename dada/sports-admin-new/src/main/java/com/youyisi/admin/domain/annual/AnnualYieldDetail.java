package com.youyisi.admin.domain.annual;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-05-14
 */


public class AnnualYieldDetail  extends BaseObject<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7491144282938138564L;
	private Long userId; //
	private Double annualYieldIncrease; //
	private Long createTime; //
	private Integer type; //
	private Long runId; //
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
	public void setAnnualYieldIncrease(Double annualYieldIncrease){
		this.annualYieldIncrease=annualYieldIncrease;
	}
	public Double getAnnualYieldIncrease(){
		return annualYieldIncrease;
	}
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
	public void setType(Integer type){
		this.type=type;
	}
	public Integer getType(){
		return type;
	}
	public void setRunId(Long runId){
		this.runId=runId;
	}
	public Long getRunId(){
		return runId;
	}
}

