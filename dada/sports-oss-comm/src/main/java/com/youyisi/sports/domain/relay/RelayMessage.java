package com.youyisi.sports.domain.relay;

import com.youyisi.sports.domain.BaseEntity;
import com.youyisi.sports.domain.user.UserLessInfo;

/**
 * @author shuye
 * @time 2016-10-08
 */


public class RelayMessage extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8789371481006913110L;
	private Long userId; //
	private Long teamId; //
	private String message; //
	private Long createTime; //
	private UserLessInfo user;
	
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
	public void setMessage(String message){
		this.message=message;
	}
	public String getMessage(){
		return message;
	}
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
	public UserLessInfo getUser() {
		return user;
	}
	public void setUser(UserLessInfo user) {
		this.user = user;
	}
}

