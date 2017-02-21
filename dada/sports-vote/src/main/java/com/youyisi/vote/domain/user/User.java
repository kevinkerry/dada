package com.youyisi.vote.domain.user;

import java.util.List;

import com.youyisi.lang.domain.BaseObject;
import com.youyisi.vote.domain.image.Image;

/**
 * user 实体类 Thu Nov 13 17:51:23 CST 2014 shuye
 */

public class User extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7939723173324485436L;
	private String name;
	private Integer age;
	private Integer sex;
	private String mobile;
	private String wechat;
	private String personalProfile;
	private String headImage;
	private Long createTime;
	private Long voteNum;
	private Integer state;
	
	private List<Image> images;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getPersonalProfile() {
		return personalProfile;
	}

	public void setPersonalProfile(String personalProfile) {
		this.personalProfile = personalProfile;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Long getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(Long voteNum) {
		this.voteNum = voteNum;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
