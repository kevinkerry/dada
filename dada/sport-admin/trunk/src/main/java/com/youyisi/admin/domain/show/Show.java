package com.youyisi.admin.domain.show;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.youyisi.admin.domain.category.Category;
import com.youyisi.admin.domain.member.Member;
import com.youyisi.admin.domain.show.showimage.ShowImage;
import com.youyisi.admin.domain.sportuser.SportUser;
import com.youyisi.lang.annotation.Id;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
public class Show implements Serializable{
    
	private static final long serialVersionUID = 6347217869466213376L;
	
	@Id
    private Long showId; //哒人秀ID
	private String showDesc; //哒人秀描述
	private String parentCategory; //父分类
	private String categoryCode; //运动分类
	private String city; //所在城市
	private String gpsX; //X座标
	private String gpsY; //Y座标
	private String location; //位置
	private Integer recommendFlag; //哒人秀推介 0推介 1不推荐
	private Integer viewQuantity; //浏览量
	private Integer focusWeight; //聚焦权重
	private Long creator; //创建人
	private Date createdTime; //创建时间
	private Long modifier; //修改人
	private Date updatedTime; //修改时间
	private String status; //数据状态
	private Category category;//哒人秀类型
	private SportUser showUser; //哒人秀创建人
	private Member member; //哒人秀创建人
	private List<ShowImage> showImages; //哒人秀照片
	private Long commentCount; //哒人秀评论数
	
	public void setShowId(Long showId){
		this.showId=showId;
	}
	public Long getShowId(){
		return showId;
	}
	public void setShowDesc(String showDesc){
		this.showDesc=showDesc;
	}
	public String getShowDesc(){
		return showDesc;
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
	public void setRecommendFlag(Integer recommendFlag){
		this.recommendFlag=recommendFlag;
	}
	public Integer getRecommendFlag(){
		return recommendFlag;
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
    public SportUser getShowUser() {
        return showUser;
    }
    public void setShowUser(SportUser showUser) {
        this.showUser = showUser;
    }
    public List<ShowImage> getShowImages() {
        return showImages;
    }
    public void setShowImages(List<ShowImage> showImages) {
        this.showImages = showImages;
    }
    public Long getCommentCount() {
        return commentCount;
    }
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
}

