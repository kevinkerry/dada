package com.youyisi.admin.domain.activity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.youyisi.admin.domain.activity.activityimage.ActivityImage;
import com.youyisi.admin.domain.category.Category;
import com.youyisi.admin.domain.user.User;
import com.youyisi.lang.annotation.Id;
   /**
    * T_SPORT_ACTIVITY 实体类
    * 2015-07-07 16 44 00 shuye
    */ 

public class Activity  implements Serializable{

	private static final long serialVersionUID = -5207648418582954754L;
	
	@Id
	private Long activityId;// 活动ID
	private String activityTitle;// 活动标题
	private String activityCode;// 活动编码
	private String activityDesc;// 活动描述
	private String logoUrl;// 封面图路径
	private String parentCategory;// 父分类
	private String categoryCode;// 运动子分类
	private Integer limitFlag;// 是否限制人数：0-不限制；1-限制
	private Integer limitNumber;// 限制人数
	private Integer feeFlag;// 是否收费 0不收费 1收费
	private String feeComments;// 费用备注
	private Integer recommendFlag;// 活动推介  0不推荐 1推荐
	private Integer activityState;// 活动状态：1-报名中；2-报名截至；3-活动满员；4-活动作废
	private Integer activityType;// 活动类型：1-官方活动；2-俱乐部活动；3-个人活动
	private String activityOrg;// 活动组织
	private String province;// 所在省份
	private String city;// 所在城市
	private String district;// 所在城区
	private String address;// 详细地址
	private String gpsX;
	private String gpsY;
	private String contact;// 联系方式
	private Date startApplyTime;// 开始报名时间
	private Date endApplyTime;// 截止报名时间
	private Date beginTime;// 活动时间
	private Date endTime;// 结束时间
	private Integer viewQuantity;// 浏览量
	private Long creator;// 创建人
	private Date createdTime;// 创建时间
	private Long modifier;// 创建人
	private Date updatedTime;// 修改时间
	private String status;// 数据状态
	private String tips;// 友情提示
	private String busInformation;// 乘车信息
	private Integer type;//'0：内部活动，1：外部活动',
	private String linkAddress;//外部链接
	private Category category;//活动类型
	private User user;// 创建人
	private List<ActivityImage> activityImages;// 活动图片
	private Long applyCount;//报名数
	private Long commentCount; //评论数
	private Integer orders;//权重，活动排序
	
	public void setActivityId(Long activityId){
	    this.activityId=activityId;
	}
	public Long getActivityId(){
		return activityId;
	}
	public void setActivityTitle(String activityTitle){
	this.activityTitle=activityTitle;
	}
	public String getActivityTitle(){
		return activityTitle;
	}
	public void setActivityCode(String activityCode){
	this.activityCode=activityCode;
	}
	public String getActivityCode(){
		return activityCode;
	}
	public void setActivityDesc(String activityDesc){
	this.activityDesc=activityDesc;
	}
	public String getActivityDesc(){
		return activityDesc;
	}
	public void setLogoUrl(String logoUrl){
	this.logoUrl=logoUrl;
	}
	public String getLogoUrl(){
		return logoUrl;
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
	public void setLimitFlag(Integer limitFlag){
	this.limitFlag=limitFlag;
	}
	public Integer getLimitFlag(){
		return limitFlag;
	}
	public void setLimitNumber(Integer limitNumber){
	this.limitNumber=limitNumber;
	}
	public Integer getLimitNumber(){
		return limitNumber;
	}
	public void setFeeFlag(Integer feeFlag){
	this.feeFlag=feeFlag;
	}
	public Integer getFeeFlag(){
		return feeFlag;
	}
	public void setFeeComments(String feeComments){
	this.feeComments=feeComments;
	}
	public String getFeeComments(){
		return feeComments;
	}
	public void setRecommendFlag(Integer recommendFlag){
	this.recommendFlag=recommendFlag;
	}
	public Integer getRecommendFlag(){
		return recommendFlag;
	}
	public void setActivityState(Integer activityState){
	this.activityState=activityState;
	}
	public Integer getActivityState(){
		return activityState;
	}
	public void setActivityType(Integer activityType){
	this.activityType=activityType;
	}
	public Integer getActivityType(){
		return activityType;
	}
	public void setActivityOrg(String activityOrg){
	this.activityOrg=activityOrg;
	}
	public String getActivityOrg(){
		return activityOrg;
	}
	public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public void setCity(String city){
	this.city=city;
	}
	public String getCity(){
		return city;
	}
	public void setDistrict(String district){
	this.district=district;
	}
	public String getDistrict(){
		return district;
	}
	public void setAddress(String address){
	this.address=address;
	}
	public String getAddress(){
		return address;
	}
	public void setGpsX(String gpsX){
	this.gpsX=gpsX;
	}
	public String getGpsX(){
		return gpsX;
	}
	public void setGpsY(String gpsY){
	this.gpsY=gpsY;
	}
	public String getGpsY(){
		return gpsY;
	}
	public void setContact(String contact){
	this.contact=contact;
	}
	public String getContact(){
		return contact;
	}
	public void setStartApplyTime(Date startApplyTime){
	this.startApplyTime=startApplyTime;
	}
	public Date getStartApplyTime(){
		return startApplyTime;
	}
	public void setEndApplyTime(Date endApplyTime){
	this.endApplyTime=endApplyTime;
	}
	public Date getEndApplyTime(){
		return endApplyTime;
	}
	public void setBeginTime(Date beginTime){
	this.beginTime=beginTime;
	}
	public Date getBeginTime(){
		return beginTime;
	}
	public void setEndTime(Date endTime){
	this.endTime=endTime;
	}
	public Date getEndTime(){
		return endTime;
	}
	public void setViewQuantity(Integer viewQuantity){
	this.viewQuantity=viewQuantity;
	}
	public Integer getViewQuantity(){
		return viewQuantity;
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
	public void setTips(String tips){
	this.tips=tips;
	}
	public String getTips(){
		return tips;
	}
	public void setBusInformation(String busInformation){
	    this.busInformation=busInformation;
	}
	public String getBusInformation(){
		return busInformation;
	}
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getLinkAddress() {
        return linkAddress;
    }
    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public List<ActivityImage> getActivityImages() {
        return activityImages;
    }
    public void setActivityImages(List<ActivityImage> activityImages) {
        this.activityImages = activityImages;
    }
    public Long getApplyCount() {
        return applyCount;
    }
    public void setApplyCount(Long applyCount) {
        this.applyCount = applyCount;
    }
    public Integer getOrders() {
        return orders;
    }
    public void setOrders(Integer orders) {
        this.orders = orders;
    }
    public Long getCommentCount() {
        return commentCount;
    }
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }
}

