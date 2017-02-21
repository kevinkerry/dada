/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.youyisi.admin.domain.intelligent;

import java.util.List;

import com.youyisi.admin.domain.intelligent.image.Image;
import com.youyisi.admin.domain.intelligent.items.SportsItems;
import com.youyisi.lang.domain.BaseObject;

public class Intelligent extends BaseObject<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5394552769206983498L;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 性别1:男，0：女，3：保密
	 */
	private Integer sex;
	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * 手机
	 */
	private String mobile;

	/**
	 * 微博
	 */
	private String weibo;
	/**
	 * 单位
	 */
	private String workUnit;
	/**
	 * 高
	 */
	private Integer height;
	/**
	 * 重
	 */
	private Integer weight;
	/**
	 * 三围
	 */
	private Double bust;
	private Double waist;
	private Double hips;
	/**
	 * 微信号
	 */
	private String wechat;
	/**
	 * 个人博客
	 */

	private String blog;
	/**
	 * 是否加入
	 */
	private Integer joinClub;
	
	private String clubName;
	/**
	 * 创建时间
	 */
	private long createOn;
	
	private Integer status;
	private Integer category;
	private Integer orders;
	private String notReason;
	private Integer recommendFlag; //达人推荐 0不推荐 1推荐

	private List<SportsItems> items;
	private List<Image> images;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public Integer getJoinClub() {
		return joinClub;
	}

	public void setJoinClub(Integer joinClub) {
		this.joinClub = joinClub;
	}

	public long getCreateOn() {
		return createOn;
	}

	public void setCreateOn(long createOn) {
		this.createOn = createOn;
	}

	public List<SportsItems> getItems() {
		return items;
	}

	public void setItems(List<SportsItems> items) {
		this.items = items;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Double getBust() {
		return bust;
	}

	public void setBust(Double bust) {
		this.bust = bust;
	}

	public Double getWaist() {
		return waist;
	}

	public void setWaist(Double waist) {
		this.waist = waist;
	}

	public Double getHips() {
		return hips;
	}

	public void setHips(Double hips) {
		this.hips = hips;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getNotReason() {
		return notReason;
	}

	public void setNotReason(String notReason) {
		this.notReason = notReason;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

    public Integer getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(Integer recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

}
