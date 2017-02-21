package com.youyisi.sports.domain.integralwall;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-08-15
 */


public class DoumIntegralWall extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6653002528103389615L;
	private Long userId; //
	private String orderid; //
	private String pubid; //
	private String ad; //
	private Integer adid; //
	private String device; //
	private Integer channel; //
	private Double price; //
	private Integer point; //
	private Integer ts; //
	private String sign; //
	private String pkg; //
	private Integer action; //
	private String actionName; //
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setOrderid(String orderid){
		this.orderid=orderid;
	}
	public String getOrderid(){
		return orderid;
	}
	public void setPubid(String pubid){
		this.pubid=pubid;
	}
	public String getPubid(){
		return pubid;
	}
	public void setAd(String ad){
		this.ad=ad;
	}
	public String getAd(){
		return ad;
	}
	public void setAdid(Integer adid){
		this.adid=adid;
	}
	public Integer getAdid(){
		return adid;
	}
	public void setDevice(String device){
		this.device=device;
	}
	public String getDevice(){
		return device;
	}
	public void setChannel(Integer channel){
		this.channel=channel;
	}
	public Integer getChannel(){
		return channel;
	}
	public void setPrice(Double price){
		this.price=price;
	}
	public Double getPrice(){
		return price;
	}
	public void setPoint(Integer point){
		this.point=point;
	}
	public Integer getPoint(){
		return point;
	}
	public void setTs(Integer ts){
		this.ts=ts;
	}
	public Integer getTs(){
		return ts;
	}
	public void setSign(String sign){
		this.sign=sign;
	}
	public String getSign(){
		return sign;
	}
	public void setPkg(String pkg){
		this.pkg=pkg;
	}
	public String getPkg(){
		return pkg;
	}
	public void setAction(Integer action){
		this.action=action;
	}
	public Integer getAction(){
		return action;
	}
	public void setActionName(String actionName){
		this.actionName=actionName;
	}
	public String getActionName(){
		return actionName;
	}
}

