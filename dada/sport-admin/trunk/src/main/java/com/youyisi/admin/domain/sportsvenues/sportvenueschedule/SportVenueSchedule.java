package com.youyisi.admin.domain.sportsvenues.sportvenueschedule;

import java.io.Serializable;

import java.util.Date;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
public class SportVenueSchedule  implements Serializable{
    
	private static final long serialVersionUID = -8494529923361324036L;
	
    private Long scheduleId; //排期流水号
	private Long venueId; //所属场馆ID
	private Long childVenueId; //所属场地
	private Long districtId; //片区ID
	private Double maketingPrice; //价格
	private Double salePrice; //销售价格
	private Date startTime; //开始时间
	private Date endTime; //结束时间
	private Integer bookingState; //预定状态 1 已预定 0未预定
	private Long creator; //创建人
	private Date createdTime; //创建时间
	private Long modifier; //修改人
	private Date updatedTime; //修改时间
	private String status; //数据状态
	
	public void setScheduleId(Long scheduleId){
		this.scheduleId=scheduleId;
	}
	public Long getScheduleId(){
		return scheduleId;
	}
	public void setVenueId(Long venueId){
		this.venueId=venueId;
	}
	public Long getVenueId(){
		return venueId;
	}
	public void setChildVenueId(Long childVenueId){
		this.childVenueId=childVenueId;
	}
	public Long getChildVenueId(){
		return childVenueId;
	}
	public void setDistrictId(Long districtId){
		this.districtId=districtId;
	}
	public Long getDistrictId(){
		return districtId;
	}
	public void setMaketingPrice(Double maketingPrice){
		this.maketingPrice=maketingPrice;
	}
	public Double getMaketingPrice(){
		return maketingPrice;
	}
	public void setSalePrice(Double salePrice){
		this.salePrice=salePrice;
	}
	public Double getSalePrice(){
		return salePrice;
	}
	public void setStartTime(Date startTime){
		this.startTime=startTime;
	}
	public Date getStartTime(){
		return startTime;
	}
	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}
	public Date getEndTime(){
		return endTime;
	}
	public void setBookingState(Integer bookingState){
		this.bookingState=bookingState;
	}
	public Integer getBookingState(){
		return bookingState;
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
	public void setModifier(Long modifier){
		this.modifier=modifier;
	}
	public Long getModifier(){
		return modifier;
	}
	public void setUpdatedTime(Date updatedTime){
		this.updatedTime=updatedTime;
	}
	public Date getUpdatedTime(){
		return updatedTime;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}
}

