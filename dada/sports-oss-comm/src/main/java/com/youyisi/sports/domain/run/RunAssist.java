package com.youyisi.sports.domain.run;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-08-16
 */


public class RunAssist extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1736798075885448165L;
	private Long runId; //
	private Long createTime; //
	private Double avspeed; //
	private Double distance; //
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setRunId(Long runId){
		this.runId=runId;
	}
	public Long getRunId(){
		return runId;
	}
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
	public void setAvspeed(Double avspeed){
		this.avspeed=avspeed;
	}
	public Double getAvspeed(){
		return avspeed;
	}
	public void setDistance(Double distance){
		this.distance=distance;
	}
	public Double getDistance(){
		return distance;
	}
}

