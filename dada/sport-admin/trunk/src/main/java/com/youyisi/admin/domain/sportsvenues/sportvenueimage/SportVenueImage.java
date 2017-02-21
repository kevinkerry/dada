package com.youyisi.admin.domain.sportsvenues.sportvenueimage;

import java.io.Serializable;
import java.util.Date;

import com.youyisi.lang.annotation.Id;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
public class SportVenueImage implements Serializable{
    
	private static final long serialVersionUID = 2378963056313067098L;
	
	@Id
    private Long imgId; //图片ID
	private Long venueId; //场馆ID
	private String imgUrl; //图片URL
	private Integer imgOrder; //图片顺序
	private Long creator; //创建人
	private Date createdTime; //创建时间
	private String status; //状态
	
	public void setImgId(Long imgId){
		this.imgId=imgId;
	}
	public Long getImgId(){
		return imgId;
	}
	public void setVenueId(Long venueId){
		this.venueId=venueId;
	}
	public Long getVenueId(){
		return venueId;
	}
	public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}
	public String getImgUrl(){
		return imgUrl;
	}
	public void setImgOrder(Integer imgOrder){
		this.imgOrder=imgOrder;
	}
	public Integer getImgOrder(){
		return imgOrder;
	}
	public void setCreator(Long creator){
		this.creator=creator;
	}
	public Long getCreator(){
		return creator;
	}
	public void setCreatedTime(Date createdTime){
		this.createdTime=createdTime;
	}
	public Date getCreatedTime(){
		return createdTime;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}
}

