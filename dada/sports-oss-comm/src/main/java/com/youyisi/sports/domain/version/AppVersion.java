package com.youyisi.sports.domain.version;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-08-30
 */


public class AppVersion extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2643427005904992711L;
	private String channel; //
	private Integer versionCode; //
	private Integer isForceUpdate; //
	private String appUrl; //
	private String versionName; //
	private Long appSize; //
	private String changeLog; //

	public void setChannel(String channel){
		this.channel=channel;
	}
	public String getChannel(){
		return channel;
	}
	public void setVersionCode(Integer versionCode){
		this.versionCode=versionCode;
	}
	public Integer getVersionCode(){
		return versionCode;
	}
	public void setIsForceUpdate(Integer isForceUpdate){
		this.isForceUpdate=isForceUpdate;
	}
	public Integer getIsForceUpdate(){
		return isForceUpdate;
	}
	public void setAppUrl(String appUrl){
		this.appUrl=appUrl;
	}
	public String getAppUrl(){
		return appUrl;
	}
	public void setVersionName(String versionName){
		this.versionName=versionName;
	}
	public String getVersionName(){
		return versionName;
	}
	public void setAppSize(Long appSize){
		this.appSize=appSize;
	}
	public Long getAppSize(){
		return appSize;
	}
	public void setChangeLog(String changeLog){
		this.changeLog=changeLog;
	}
	public String getChangeLog(){
		return changeLog;
	}
}

