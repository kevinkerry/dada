package com.youyisi.admin.domain.topic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.youyisi.admin.domain.category.Category;
import com.youyisi.admin.domain.sportuser.SportUser;
import com.youyisi.admin.domain.topic.topicimage.TopicImage;
import com.youyisi.lang.annotation.Id;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
public class Topic implements Serializable{
    
	private static final long serialVersionUID = -7040920234950970276L;
	
	@Id
    private Long topicId; //话题ID
	private String topicContent; //话题内容
	private String parentCategory; //父分类
	private String categoryCode; //运动分类
	private String city; //所在城市
	private String gpsX; //X座标
	private String gpsY; //Y座标
	private String location; //位置
	private Integer viewQuantity; //浏览量
	private Integer focusWeight; //聚焦权重
	private Long creator; //创建人
	private Date createdTime; //创建时间
	private Long modifier; //修改人
	private Date updatedTime; //修改时间
	private String status; //数据状态
	private Category category;//话题类型
	private List<TopicImage> topicImages; //约运动话题图片
	private Long commentCount; //约运动话题评论数量
	private SportUser topicUser; //话题发起人
	
	public void setTopicId(Long topicId){
		this.topicId=topicId;
	}
	public Long getTopicId(){
		return topicId;
	}
	public void setTopicContent(String topicContent){
		this.topicContent=topicContent;
	}
	public String getTopicContent(){
		return topicContent;
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
	public void setCity(String city){
		this.city=city;
	}
	public String getCity(){
		return city;
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
	public void setLocation(String location){
		this.location=location;
	}
	public String getLocation(){
		return location;
	}
	public void setViewQuantity(Integer viewQuantity){
		this.viewQuantity=viewQuantity;
	}
	public Integer getViewQuantity(){
		return viewQuantity;
	}
	public void setFocusWeight(Integer focusWeight){
		this.focusWeight=focusWeight;
	}
	public Integer getFocusWeight(){
		return focusWeight;
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
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Long getCommentCount() {
        return commentCount;
    }
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }
    public List<TopicImage> getTopicImages() {
        return topicImages;
    }
    public void setTopicImages(List<TopicImage> topicImages) {
        this.topicImages = topicImages;
    }
    public SportUser getTopicUser() {
        return topicUser;
    }
    public void setTopicUser(SportUser topicUser) {
        this.topicUser = topicUser;
    }
}

