package com.youyisi.sports.domain.relay;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-10-08
 */


public class RelayMemberFavour extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3066049770248006408L;
	private Long userId; //
	private Long relayMemberId; //
	private Integer type; //
	private Long date; //
	private Long createTime; //

	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setRelayMemberId(Long relayMemberId){
		this.relayMemberId=relayMemberId;
	}
	public Long getRelayMemberId(){
		return relayMemberId;
	}
	public void setType(Integer type){
		this.type=type;
	}
	public Integer getType(){
		return type;
	}
	public void setDate(Long date){
		this.date=date;
	}
	public Long getDate(){
		return date;
	}
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
}

