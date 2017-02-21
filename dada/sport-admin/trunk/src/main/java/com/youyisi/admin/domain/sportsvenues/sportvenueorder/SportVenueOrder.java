package com.youyisi.admin.domain.sportsvenues.sportvenueorder;

import java.io.Serializable;
import java.util.Date;

import com.youyisi.lang.annotation.Id;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
public class SportVenueOrder  implements Serializable{
    
	private static final long serialVersionUID = 6788623129071877774L;
	
	@Id
    private Long venueOrderId; //订单ID
	private Long venueOrderCode; //所属场地
	private Long sportVenueId; //所属场馆
	private Double orderAmount; //订单总金额
	private Double payAmount; //支付总金额
	private Integer orderStatus; //订单状态
	private Integer payStatus; //支付状态：1-待支付；2-已支付
	private Integer syncStatus; //同步状态：1-待处理、2-推送成功、0-推送失败，网络异常、-1-推送失败、未知异常
	private String externalOrderId; //第三方订单ID
	private Long creator; //下单人
	private Date createdTime; //下单时间
	private Long modifier; //修改人
	private Date updatedTime; //修改时间
	private String status; //数据状态
	
	public void setVenueOrderId(Long venueOrderId){
		this.venueOrderId=venueOrderId;
	}
	public Long getVenueOrderId(){
		return venueOrderId;
	}
	public void setVenueOrderCode(Long venueOrderCode){
		this.venueOrderCode=venueOrderCode;
	}
	public Long getVenueOrderCode(){
		return venueOrderCode;
	}
	public void setSportVenueId(Long sportVenueId){
		this.sportVenueId=sportVenueId;
	}
	public Long getSportVenueId(){
		return sportVenueId;
	}
	public void setOrderAmount(Double orderAmount){
		this.orderAmount=orderAmount;
	}
	public Double getOrderAmount(){
		return orderAmount;
	}
	public void setPayAmount(Double payAmount){
		this.payAmount=payAmount;
	}
	public Double getPayAmount(){
		return payAmount;
	}
	public void setOrderStatus(Integer orderStatus){
		this.orderStatus=orderStatus;
	}
	public Integer getOrderStatus(){
		return orderStatus;
	}
	public void setPayStatus(Integer payStatus){
		this.payStatus=payStatus;
	}
	public Integer getPayStatus(){
		return payStatus;
	}
	public void setSyncStatus(Integer syncStatus){
		this.syncStatus=syncStatus;
	}
	public Integer getSyncStatus(){
		return syncStatus;
	}
	public void setExternalOrderId(String externalOrderId){
		this.externalOrderId=externalOrderId;
	}
	public String getExternalOrderId(){
		return externalOrderId;
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

