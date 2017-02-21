package com.youyisi.vote.domain.weixin;

/**
 * 音乐消息
 * 
 * @author liufeng
 * @date 2013-05-19
 */
public class ImageMessage extends BaseMessage {
	// 音乐
	private MediaImage Image;

	public MediaImage getImage() {
		return Image;
	}

	public void setImage(MediaImage image) {
		Image = image;
	}

	
}