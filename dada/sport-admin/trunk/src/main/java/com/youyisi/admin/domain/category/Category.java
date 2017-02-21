package com.youyisi.admin.domain.category;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author yinjunfeng
 * @date Jul 8, 2015
 */
public class Category implements Serializable{
    
	private static final long serialVersionUID = 5692524774726571645L;
	
    private Long categoryId; //分类ID
	private String categoryName; //分类名称
	private String categoryCode; //分类编码
	private Integer parentId; //父分类ID
	private String parentCode; //父分类编码
	private Integer categoryLevel; //分类级别
	private String categoryDesc; //分类描述
	private String categoryLogoUrl; //分类LOGO
	private Long creator; //创建人
	private Date createdTime; //创建时间
	private Long modifier; //修改人
	private Date updatedTime; //修改时间
	private String status; //数据状态
	
	public void setCategoryId(Long categoryId){
	    this.categoryId=categoryId;
	}
	public Long getCategoryId(){
		return categoryId;
	}
	public void setCategoryName(String categoryName){
	    this.categoryName=categoryName;
	}
	public String getCategoryName(){
		return categoryName;
	}
	public void setCategoryCode(String categoryCode){
	    this.categoryCode=categoryCode;
	}
	public String getCategoryCode(){
		return categoryCode;
	}
	public void setParentId(Integer parentId){
	    this.parentId=parentId;
	}
	public Integer getParentId(){
		return parentId;
	}
	public void setParentCode(String parentCode){
	    this.parentCode=parentCode;
	}
	public String getParentCode(){
		return parentCode;
	}
	public void setCategoryLevel(Integer categoryLevel){
	    this.categoryLevel=categoryLevel;
	}
	public Integer getCategoryLevel(){
		return categoryLevel;
	}
	public void setCategoryDesc(String categoryDesc){
	    this.categoryDesc=categoryDesc;
	}
	public String getCategoryDesc(){
		return categoryDesc;
	}
	public void setCategoryLogoUrl(String categoryLogoUrl){
	    this.categoryLogoUrl=categoryLogoUrl;
	}
	public String getCategoryLogoUrl(){
		return categoryLogoUrl;
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

