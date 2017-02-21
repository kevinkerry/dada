package com.youyisi.sports.domain.activity;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-07-13
 */


public class ActivityWithInviteFriendActivity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3806498690356866884L;
	private String title; //
	private String cover; //
	private Long hot; //
	private Integer type; //
	private Long beginTime; //
	private Long endTime; //
	private Long date; //
	private Integer status; //
	private Long createTime; //
	private Long updateTime; //
	private InviteFriendActivity inviteFriendActivity;
	

	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setCover(String cover){
		this.cover=cover;
	}
	public String getCover(){
		return cover;
	}
	public void setHot(Long hot){
		this.hot=hot;
	}
	public Long getHot(){
		return hot;
	}
	public void setType(Integer type){
		this.type=type;
	}
	public Integer getType(){
		return type;
	}
	public void setBeginTime(Long beginTime){
		this.beginTime=beginTime;
	}
	public Long getBeginTime(){
		return beginTime;
	}
	public void setEndTime(Long endTime){
		this.endTime=endTime;
	}
	public Long getEndTime(){
		return endTime;
	}
	public void setDate(Long date){
		this.date=date;
	}
	public Long getDate(){
		return date;
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
	public InviteFriendActivity getInviteFriendActivity() {
		return inviteFriendActivity;
	}
	public void setInviteFriendActivity(InviteFriendActivity inviteFriendActivity) {
		this.inviteFriendActivity = inviteFriendActivity;
	}
}

