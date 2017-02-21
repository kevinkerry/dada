package com.youyisi.sports.domain.relay;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-09-05
 */


public class RelayTeamWithMore extends BaseEntity implements Comparable<RelayTeamWithMore>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5946307494339981472L;
	private Long activityId; //
	private String name; //
	private String logo; //
	private Long userId; //
	private Integer status; //
	private Long createTime; //
	private Long updateTime; //
	private Integer memberCount; //
	private Double distance; //
	
	public void setActivityId(Long activityId){
		this.activityId=activityId;
	}
	public Long getActivityId(){
		return activityId;
	}
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
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
	public void setUpdateTime(Long updateTime){
		this.updateTime=updateTime;
	}
	public Long getUpdateTime(){
		return updateTime;
	}
	public Integer getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
	@Override
	public int compareTo(RelayTeamWithMore o) {
		double thismoney = 0.0;
		double othermoney = 0.0;
		if(this.getDistance()!=null){
			thismoney=this.getDistance();
		}
		if(o.getDistance()!=null){
			othermoney=o.getDistance();
		}
		if(thismoney>othermoney){
			return -1;
		}else{
			return 1;
		}
	}
}

