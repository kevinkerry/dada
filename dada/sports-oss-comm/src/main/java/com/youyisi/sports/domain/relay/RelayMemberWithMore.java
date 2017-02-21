package com.youyisi.sports.domain.relay;

import com.youyisi.sports.domain.BaseEntity;
import com.youyisi.sports.domain.user.UserLessInfo;

/**
 * @author shuye
 * @time 2016-09-05
 */


public class RelayMemberWithMore extends BaseEntity{
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
	
	private Integer relayBatonCount; //
	private Double distance; //
	private UserLessInfo user;
	
	private Integer praise; //
	private Integer cheer; //
	private Integer havePraise; //
	private Integer haveCheer; //
	
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
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public UserLessInfo getUser() {
		return user;
	}
	public void setUser(UserLessInfo user) {
		this.user = user;
	}
	public Integer getRelayBatonCount() {
		return relayBatonCount;
	}
	public void setRelayBatonCount(Integer relayBatonCount) {
		this.relayBatonCount = relayBatonCount;
	}
	public Integer getPraise() {
		return praise;
	}
	public void setPraise(Integer praise) {
		this.praise = praise;
	}
	public Integer getCheer() {
		return cheer;
	}
	public void setCheer(Integer cheer) {
		this.cheer = cheer;
	}
	public Integer getHavePraise() {
		return havePraise;
	}
	public void setHavePraise(Integer havePraise) {
		this.havePraise = havePraise;
	}
	public Integer getHaveCheer() {
		return haveCheer;
	}
	public void setHaveCheer(Integer haveCheer) {
		this.haveCheer = haveCheer;
	}
}

