package com.youyisi.sports.domain.relay;

import java.util.List;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-09-05
 */


public class RelayMemberWithChildren extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2142342825672914701L;
	private Long activityId; //
	private Long userId; //
	private Long teamId; //
	private Long parentId; //
	private Integer payStatus; //
	private Integer status; //
	private Long createTime; //
	private Integer level; //
	private String usercode; //
	List<RelayMemberWithChildren> children;
	
	public void setActivityId(Long activityId){
		this.activityId=activityId;
	}
	public Long getActivityId(){
		return activityId;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setTeamId(Long teamId){
		this.teamId=teamId;
	}
	public Long getTeamId(){
		return teamId;
	}
	public void setParentId(Long parentId){
		this.parentId=parentId;
	}
	public Long getParentId(){
		return parentId;
	}
	public void setPayStatus(Integer payStatus){
		this.payStatus=payStatus;
	}
	public Integer getPayStatus(){
		return payStatus;
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
	public void setLevel(Integer level){
		this.level=level;
	}
	public Integer getLevel(){
		return level;
	}
	public void setUsercode(String usercode){
		this.usercode=usercode;
	}
	public String getUsercode(){
		return usercode;
	}
	public List<RelayMemberWithChildren> getChildren() {
		return children;
	}
	public void setChildren(List<RelayMemberWithChildren> children) {
		this.children = children;
	}
}

