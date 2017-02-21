package com.youyisi.admin.domain.sportsvenues;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.youyisi.admin.domain.sportsvenues.sportvenueimage.SportVenueImage;
import com.youyisi.lang.annotation.Id;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
public class SportsVenues  implements Serializable{
    
	private static final long serialVersionUID = 2459859105502451624L;
	
	@Id
    private Long venueId; //场馆ID
	private String venueName; //场馆名称
	private String venueCode; //场馆编码
	private String venueDesc; //场馆介绍
	private String venueLogo; //场馆标志图
	private String venueContact; //联系方式
	private String province; //所在省份
	private String city; //所在城市
	private String district; //所在城区
	private String address; //详细地址
	private String busInformation; //乘车信息
	private String facility; //场馆设施
	private String gpsX; //X座标
	private String gpsY; //Y座标
	private Integer venueGrade; //场馆等级
	private Integer venueChannel; //场馆渠道:1-自营、2-合约制、3-第三方  
	private String merchantCode; //所属商家
	private String externalCode; //第三方编码
	private Long creator; //创建人
	private Date createdTime; //创建时间
	private Long modifier; //修改人
	private Date updatedTime; //修改时间
	private String status; //数据状态
	private List<SportVenueImage> venueImages;//场馆照片 
	private Long venueChileCount;//子场地数
	private List<Map<Object, Object>> venueDistrictCounts;//场馆内子场地片区数
	
    public Long getVenueId() {
        return venueId;
    }
    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }
    public String getVenueName() {
        return venueName;
    }
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
    public String getVenueCode() {
        return venueCode;
    }
    public void setVenueCode(String venueCode) {
        this.venueCode = venueCode;
    }
    public String getVenueDesc() {
        return venueDesc;
    }
    public void setVenueDesc(String venueDesc) {
        this.venueDesc = venueDesc;
    }
    public String getVenueLogo() {
        return venueLogo;
    }
    public void setVenueLogo(String venueLogo) {
        this.venueLogo = venueLogo;
    }
    public String getVenueContact() {
        return venueContact;
    }
    public void setVenueContact(String venueContact) {
        this.venueContact = venueContact;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getBusInformation() {
        return busInformation;
    }
    public void setBusInformation(String busInformation) {
        this.busInformation = busInformation;
    }
    public String getFacility() {
        return facility;
    }
    public void setFacility(String facility) {
        this.facility = facility;
    }
    public String getGpsX() {
        return gpsX;
    }
    public void setGpsX(String gpsX) {
        this.gpsX = gpsX;
    }
    public String getGpsY() {
        return gpsY;
    }
    public void setGpsY(String gpsY) {
        this.gpsY = gpsY;
    }
    public Integer getVenueGrade() {
        return venueGrade;
    }
    public void setVenueGrade(Integer venueGrade) {
        this.venueGrade = venueGrade;
    }
    public Integer getVenueChannel() {
        return venueChannel;
    }
    public void setVenueChannel(Integer venueChannel) {
        this.venueChannel = venueChannel;
    }
    public String getMerchantCode() {
        return merchantCode;
    }
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }
    public String getExternalCode() {
        return externalCode;
    }
    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
    }
    public Long getCreator() {
        return creator;
    }
    public void setCreator(Long creator) {
        this.creator = creator;
    }
    public Date getCreatedTime() {
        return createdTime;
    }
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
    public Long getModifier() {
        return modifier;
    }
    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }
    public Date getUpdatedTime() {
        return updatedTime;
    }
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<SportVenueImage> getVenueImages() {
        return venueImages;
    }
    public void setVenueImages(List<SportVenueImage> venueImages) {
        this.venueImages = venueImages;
    }
    public Long getVenueChileCount() {
        return venueChileCount;
    }
    public void setVenueChileCount(Long venueChileCount) {
        this.venueChileCount = venueChileCount;
    }
    public List<Map<Object, Object>> getVenueDistrictCounts() {
        return venueDistrictCounts;
    }
    public void setVenueDistrictCounts(List<Map<Object, Object>> venueDistrictCounts) {
        this.venueDistrictCounts = venueDistrictCounts;
    }
}

