package com.youyisi.sports.domain.user;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-05-10
 */

/** 排除以下字段 不返回为Json **/
public class UserLessInfo extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9067720393446189950L;

	private String nickname; //
	private String headPortrait; //
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

}
