package com.youyisi.admin.domain.sportsvenues.sportvenuedistrict;

import java.io.Serializable;
import java.util.Date;

import com.youyisi.admin.domain.sportsvenues.sportsvenueschild.SportsVenuesChild;
import com.youyisi.lang.annotation.Id;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
public class SportVenueDistrict implements Serializable{
    
	private static final long serialVersionUID = -5105122105403260828L;
	
	@Id
    private Long districtId; //片区ID
	private Long venueId; //场馆ID
	private String districtCode;//片区编号
	private Long childVenueId; //子场地ID
	private Integer limitNumber; //限制人数  0为不限制
	private Double area; //片区面积
	private String lights; //灯光效果
	private Double height; //片区高度
	private String material; //地板材质
	private Integer districtStatus; //片区状态 1 开放中 2 未开放
	private Long creator; //创建人
	private Date createdTime; //创建时间
	private Long modifier; //修改人
	private Date updatedTime; //更新时间
	private String status; //数据状态
	private SportsVenuesChild sportsVenuesChild;//所属场地
	
	public void setDistrictId(Long districtId){
		this.districtId=districtId;
	}
	public Long getDistrictId(){
		return districtId;
	}
	public void setVenueId(Long venueId){
		this.venueId=venueId;
	}
	public Long getVenueId(){
		return venueId;
	}
    public String getDistrictCode() {
        return districtCode;
    }
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }
    public void setChildVenueId(Long childVenueId){
		this.childVenueId=childVenueId;
	}
	public Long getChildVenueId(){
		return childVenueId;
	}
	public void setLimitNumber(Integer limitNumber){
		this.limitNumber=limitNumber;
	}
	public Integer getLimitNumber(){
		return limitNumber;
	}
	public void setArea(Double area){
		this.area=area;
	}
	public Double getArea(){
		return area;
	}
	public void setLights(String lights){
		this.lights=lights;
	}
	public String getLights(){
		return lights;
	}
	public void setHeight(Double height){
		this.height=height;
	}
	public Double getHeight(){
		return height;
	}
	public void setMaterial(String material){
		this.material=material;
	}
	public String getMaterial(){
		return material;
	}
	public void setDistrictStatus(Integer districtStatus){
		this.districtStatus=districtStatus;
	}
	public Integer getDistrictStatus(){
		return districtStatus;
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
    public SportsVenuesChild getSportsVenuesChild() {
        return sportsVenuesChild;
    }
    public void setSportsVenuesChild(SportsVenuesChild sportsVenuesChild) {
        this.sportsVenuesChild = sportsVenuesChild;
    }
}

