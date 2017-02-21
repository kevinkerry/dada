package com.youyisi.admin.domain.sportsvenues.sportsvenueschild;

import java.io.Serializable;
import java.util.Date;

import com.youyisi.admin.domain.category.Category;
import com.youyisi.admin.domain.sportsvenues.SportsVenues;
import com.youyisi.lang.annotation.Id;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
public class SportsVenuesChild  implements Serializable{
    
	private static final long serialVersionUID = -8004414425108643094L;
	
	@Id
    private Long childVenueId; //子场地ID
	private String childVenueName; //子场地名称
	private String childVenueCode; //子场地编码
	private Long venueId; //所属场馆ID
	private String venueCode; //所属场馆编码     
	private String childVenueDesc; //子场地描述
	private String parentCategory; //父分类
	private String categoryCode; //子分类
	private String venueContact; //联系方式
	private Double maketingPrice; //市场标准价格
	private Double salePrice; //促销价格
	private Integer childVenueCount; //场地数量
	private String bookingStartTime; //预定开始时间
	private String bookingEndTime; //预定截至时间
	private Integer bookingTimeInteral; //预定场地时长（分钟）
	private Integer venueChannel; //场馆渠道
	private String merchantCode; //服务商家编码
	private String externalCode; //外部场地编码
	private Long creator; //创建人
	private Date createdTime; //创建时间
	private Long modifier; //修改人
	private Date updatedTime; //修改时间
	private String status; //数据状态
	private Long venueChildDistrictCount;//子场地片区数
	private SportsVenues sportsVenue;//所属场馆
	private Category category;//所属类型
	
	public void setChildVenueId(Long childVenueId){
		this.childVenueId=childVenueId;
	}
	public Long getChildVenueId(){
		return childVenueId;
	}
	public void setChildVenueName(String childVenueName){
		this.childVenueName=childVenueName;
	}
	public String getChildVenueName(){
		return childVenueName;
	}
	public void setChildVenueCode(String childVenueCode){
		this.childVenueCode=childVenueCode;
	}
	public String getChildVenueCode(){
		return childVenueCode;
	}
	public void setVenueId(Long venueId){
		this.venueId=venueId;
	}
	public Long getVenueId(){
		return venueId;
	}
	public void setVenueCode(String venueCode){
		this.venueCode=venueCode;
	}
	public String getVenueCode(){
		return venueCode;
	}
	public void setChildVenueDesc(String childVenueDesc){
		this.childVenueDesc=childVenueDesc;
	}
	public String getChildVenueDesc(){
		return childVenueDesc;
	}
	public void setParentCategory(String parentCategory){
		this.parentCategory=parentCategory;
	}
	public String getParentCategory(){
		return parentCategory;
	}
	public void setCategoryCode(String categoryCode){
		this.categoryCode=categoryCode;
	}
	public String getCategoryCode(){
		return categoryCode;
	}
	public void setVenueContact(String venueContact){
		this.venueContact=venueContact;
	}
	public String getVenueContact(){
		return venueContact;
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
	public void setChildVenueCount(Integer childVenueCount){
		this.childVenueCount=childVenueCount;
	}
	public Integer getChildVenueCount(){
		return childVenueCount;
	}
	public void setBookingStartTime(String bookingStartTime){
		this.bookingStartTime=bookingStartTime;
	}
	public String getBookingStartTime(){
		return bookingStartTime;
	}
	public void setBookingEndTime(String bookingEndTime){
		this.bookingEndTime=bookingEndTime;
	}
	public String getBookingEndTime(){
		return bookingEndTime;
	}
	public void setBookingTimeInteral(Integer bookingTimeInteral){
		this.bookingTimeInteral=bookingTimeInteral;
	}
	public Integer getBookingTimeInteral(){
		return bookingTimeInteral;
	}
	public void setVenueChannel(Integer venueChannel){
		this.venueChannel=venueChannel;
	}
	public Integer getVenueChannel(){
		return venueChannel;
	}
	public void setMerchantCode(String merchantCode){
		this.merchantCode=merchantCode;
	}
	public String getMerchantCode(){
		return merchantCode;
	}
	public void setExternalCode(String externalCode){
		this.externalCode=externalCode;
	}
	public String getExternalCode(){
		return externalCode;
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
    public Long getVenueChildDistrictCount() {
        return venueChildDistrictCount;
    }
    public void setVenueChildDistrictCount(Long venueChildDistrictCount) {
        this.venueChildDistrictCount = venueChildDistrictCount;
    }
    public SportsVenues getSportsVenue() {
        return sportsVenue;
    }
    public void setSportsVenue(SportsVenues sportsVenue) {
        this.sportsVenue = sportsVenue;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}

