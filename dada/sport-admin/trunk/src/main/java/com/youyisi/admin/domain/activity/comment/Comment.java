package com.youyisi.admin.domain.activity.comment;

import java.io.Serializable;
import java.util.Date;

import com.youyisi.admin.domain.sportuser.SportUser;
import com.youyisi.lang.annotation.Id;

/**
 * @author yinjunfeng
 * @time 2015-08-03
 */
public class Comment implements Serializable{
    
	private static final long serialVersionUID = -3750722385602312766L;
	
	@Id
    private Long commentsId; //评论ID
	private Long activityId; //活动ID
	private Integer commentType; //评论类型：1-评论；2-赞
	private String comments; //评论内容
	private Long parentCommentsId; //上级评论ID
	private Long creator; //评论人
	private Date createdTime; //评论时间
	private Long modifier; //修改人
	private Date updatedTime; //修改时间
	private String status; //数据状态
	private SportUser commentUser; //评论人
	
	public void setCommentsId(Long commentsId){
		this.commentsId=commentsId;
	}
	public Long getCommentsId(){
		return commentsId;
	}
	public void setActivityId(Long activityId){
		this.activityId=activityId;
	}
	public Long getActivityId(){
		return activityId;
	}
	public void setCommentType(Integer commentType){
		this.commentType=commentType;
	}
	public Integer getCommentType(){
		return commentType;
	}
	public void setComments(String comments){
		this.comments=comments;
	}
	public String getComments(){
		return comments;
	}
	public void setParentCommentsId(Long parentCommentsId){
		this.parentCommentsId=parentCommentsId;
	}
	public Long getParentCommentsId(){
		return parentCommentsId;
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
    public SportUser getCommentUser() {
        return commentUser;
    }
    public void setCommentUser(SportUser commentUser) {
        this.commentUser = commentUser;
    }
}

