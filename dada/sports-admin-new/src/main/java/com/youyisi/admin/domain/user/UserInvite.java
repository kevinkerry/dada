package com.youyisi.admin.domain.user;

import com.youyisi.lang.domain.BaseObject;

/**
 * 
 * @author hetao
 * @date 2016年8月19日 下午5:12:46
 * @version 1.0
 * 
 */
public class UserInvite extends BaseObject<Long> {

	private static final long serialVersionUID = -6190787883814327684L;

	private String usercode;

	private String nickname;

	private Long updateTime;

	private Long createTime;

	private Integer inviteNum;

	private Double countExperience;

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getInviteNum() {
		return inviteNum;
	}

	public void setInviteNum(Integer inviteNum) {
		this.inviteNum = inviteNum;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Double getCountExperience() {
		return countExperience;
	}

	public void setCountExperience(Double countExperience) {
		this.countExperience = countExperience;
	}

}
