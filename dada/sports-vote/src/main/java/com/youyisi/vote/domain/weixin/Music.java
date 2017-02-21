package com.youyisi.vote.domain.weixin;

/**
 * 闊充箰model
 * 
 * @author liufeng
 * @date 2013-05-19
 */
public class Music {
	// 闊充箰鍚嶇О
	private String Title;
	// 闊充箰鎻忚堪
	private String Description;
	// 闊充箰閾炬帴
	private String MusicUrl;
	// 楂樿川閲忛煶涔愰摼鎺ワ紝WIFI鐜浼樺厛浣跨敤璇ラ摼鎺ユ挱鏀鹃煶涔�
	private String HQMusicUrl;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String musicUrl) {
		HQMusicUrl = musicUrl;
	}

}