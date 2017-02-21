package com.youyisi.admin.domain.member.memberimage;

import java.io.Serializable;
import java.util.Date;

import com.youyisi.lang.annotation.Id;

/**
 * 
 * @author yinjunfeng
 * @date Jul 24, 2015
 */
public class MemberImage implements Serializable{
    
	private static final long serialVersionUID = -8107482283067716915L;
	
	@Id
    private Long imgId; //图象ID
	private Long userId; //用户ID
	private Long memberId; //会员ID
	private String imgUrl; //图片路径
	private Integer imgOrder; //显示顺序
	private Integer isMaster; //是否主图：1-主图；2-非主图
	private Long creator; //创建人
	private Date createdTime; //创建时间
	private Long modifier; //修改人
	private Date updatedTime; //修改时间
	private String status; //状态
	
    public Long getImgId() {
        return imgId;
    }
    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getMemberId() {
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public Integer getImgOrder() {
        return imgOrder;
    }
    public void setImgOrder(Integer imgOrder) {
        this.imgOrder = imgOrder;
    }
    public Integer getIsMaster() {
        return isMaster;
    }
    public void setIsMaster(Integer isMaster) {
        this.isMaster = isMaster;
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
}

