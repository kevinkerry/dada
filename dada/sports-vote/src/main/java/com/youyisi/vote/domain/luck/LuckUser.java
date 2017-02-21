package com.youyisi.vote.domain.luck;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2015-09-15
 */


public class LuckUser  extends BaseObject<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6941706440172915633L;
	private Long userId; //
	private Long luckId; //
	private UserInfo userInfo;
	private Luck luck;
	private Long createTime; //
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setLuckId(Long luckId){
		this.luckId=luckId;
	}
	public Long getLuckId(){
		return luckId;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public Luck getLuck() {
		return luck;
	}
	public void setLuck(Luck luck) {
		this.luck = luck;
	}
}

