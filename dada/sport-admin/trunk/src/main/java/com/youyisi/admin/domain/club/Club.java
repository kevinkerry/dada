package com.youyisi.admin.domain.club;

import java.io.Serializable;
import java.util.Date;

import com.youyisi.admin.domain.category.Category;
import com.youyisi.admin.domain.sportuser.SportUser;
import com.youyisi.lang.annotation.Id;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
public class Club implements Serializable{
    
	private static final long serialVersionUID = -7192411896213071848L;
	
	@Id
    private Long clubId; //俱乐部ID
	private String clubName; //俱乐部名称
	private String clubCode; //俱乐部代码
	private String parentCategory; //父分类
	private String categoryCode; //分类代码
	private String clubCulture; //俱乐部文化
	private String clubDesc; //俱乐部描述
	private String clubLogo; //俱乐部图片
	private Integer clubOwner; //俱乐部持有者
	private String city; //城市
	private Integer feeFlag; //是否收费 0收费 1不收费
	private String feeComments; //费用备注
	private Integer recommendFlag; //活动推荐 0 不推荐 1推荐
	private String district; //地区
	private String gpsX; //维度
	private String gpsY; //经度
	private Integer clubLevel; //俱乐部级别
	private Integer activeIndex; //活跃指数
	private Long creator; //创建人
	private Date createdTime; //创建时间
	private Long modifier; //修改人
	private Date updatedTime; //修改时间
	private String status; //状态
	private Long clubMemberCount; //俱乐部成员数量
	private SportUser owner; //俱乐部持有者
	private SportUser clubCreator; //俱乐部创建人
	private Category category; //俱乐部类型
	
	public void setClubId(Long clubId){
		this.clubId=clubId;
	}
	public Long getClubId(){
		return clubId;
	}
	public void setClubName(String clubName){
		this.clubName=clubName;
	}
	public String getClubName(){
		return clubName;
	}
	public void setClubCode(String clubCode){
		this.clubCode=clubCode;
	}
	public String getClubCode(){
		return clubCode;
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
	public void setClubCulture(String clubCulture){
		this.clubCulture=clubCulture;
	}
	public String getClubCulture(){
		return clubCulture;
	}
	public void setClubDesc(String clubDesc){
		this.clubDesc=clubDesc;
	}
	public String getClubDesc(){
		return clubDesc;
	}
	public void setClubLogo(String clubLogo){
		this.clubLogo=clubLogo;
	}
	public String getClubLogo(){
		return clubLogo;
	}
	public void setClubOwner(Integer clubOwner){
		this.clubOwner=clubOwner;
	}
	public Integer getClubOwner(){
		return clubOwner;
	}
	public void setCity(String city){
		this.city=city;
	}
	public String getCity(){
		return city;
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
	public void setDistrict(String district){
		this.district=district;
	}
	public String getDistrict(){
		return district;
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
	public void setClubLevel(Integer clubLevel){
		this.clubLevel=clubLevel;
	}
	public Integer getClubLevel(){
		return clubLevel;
	}
	public void setActiveIndex(Integer activeIndex){
		this.activeIndex=activeIndex;
	}
	public Integer getActiveIndex(){
		return activeIndex;
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
    public Long getClubMemberCount() {
        return clubMemberCount;
    }
    public void setClubMemberCount(Long clubMemberCount) {
        this.clubMemberCount = clubMemberCount;
    }
    public SportUser getOwner() {
        return owner;
    }
    public void setOwner(SportUser owner) {
        this.owner = owner;
    }
    public SportUser getClubCreator() {
        return clubCreator;
    }
    public void setClubCreator(SportUser clubCreator) {
        this.clubCreator = clubCreator;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}

