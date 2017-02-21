package com.youyisi.admin.domain.intelligent.image;

import com.youyisi.lang.domain.BaseObject;

public class Image  extends BaseObject<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4078482373353529041L;
	
	private Long userId;
	private String imageUrl;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
