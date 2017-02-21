package com.youyisi.admin.domain.sportsvenues.sportvenueorderitem;

import java.io.Serializable;

import java.util.Date;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
public class SportVenueOrderItem  implements Serializable{
    
	private static final long serialVersionUID = -2874522916505136081L;
	
    private Long orderItemId; //订单明细ID
	private Long orderItemCode; //订单明细编码
	private Long venueId; //所属场馆
	private String childVenueId; //所属子场地
	private String childVenueNo; //子场地编码
	private String venueScheduleId; //排期ID
	private String startTime; //开始时间
	private String endTime; //结束时间
	private String totalTime; //总预定时间
	private Double marketingPrice; //市场价格
	private Double salePrice; //销售价格
	private Double payPrice; //支付价格
	private Long creator; //下单人
	private Date createdTime; //下单时间
	private Long modifier; //修改人
	private Date updatedTime; //修改时间
	private String status; //数据状态
	
	public void setOrderItemId(Long orderItemId){
		this.orderItemId=orderItemId;
	}
	public Long getOrderItemId(){
		return orderItemId;
	}
	public void setOrderItemCode(Long orderItemCode){
		this.orderItemCode=orderItemCode;
	}
	public Long getOrderItemCode(){
		return orderItemCode;
	}
	public void setVenueId(Long venueId){
		this.venueId=venueId;
	}
	public Long getVenueId(){
		return venueId;
	}
	public void setChildVenueId(String childVenueId){
		this.childVenueId=childVenueId;
	}
	public String getChildVenueId(){
		return childVenueId;
	}
	public void setChildVenueNo(String childVenueNo){
		this.childVenueNo=childVenueNo;
	}
	public String getChildVenueNo(){
		return childVenueNo;
	}
	public void setVenueScheduleId(String venueScheduleId){
		this.venueScheduleId=venueScheduleId;
	}
	public String getVenueScheduleId(){
		return venueScheduleId;
	}
	public void setStartTime(String startTime){
		this.startTime=startTime;
	}
	public String getStartTime(){
		return startTime;
	}
	public void setEndTime(String endTime){
		this.endTime=endTime;
	}
	public String getEndTime(){
		return endTime;
	}
	public void setTotalTime(String totalTime){
		this.totalTime=totalTime;
	}
	public String getTotalTime(){
		return totalTime;
	}
	public void setMarketingPrice(Double marketingPrice){
		this.marketingPrice=marketingPrice;
	}
	public Double getMarketingPrice(){
		return marketingPrice;
	}
	public void setSalePrice(Double salePrice){
		this.salePrice=salePrice;
	}
	public Double getSalePrice(){
		return salePrice;
	}
	public void setPayPrice(Double payPrice){
		this.payPrice=payPrice;
	}
	public Double getPayPrice(){
		return payPrice;
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

