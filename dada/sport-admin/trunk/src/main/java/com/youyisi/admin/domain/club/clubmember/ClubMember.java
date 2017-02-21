package com.youyisi.admin.domain.club.clubmember;

import java.io.Serializable;
import java.util.Date;

import com.youyisi.admin.domain.sportuser.SportUser;
import com.youyisi.lang.annotation.Id;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
public class ClubMember implements Serializable{
    
	private static final long serialVersionUID = -7887384583374634819L;
	
	@Id
    private Long clubMemberId; //俱乐部主键
	private Long clubId; //俱乐部ID
	private Long userId; //用户ID
	private Integer relationFlag; //申请状态:1 待审批 2审批通过成为会员 3审批拒绝
	private Integer roleType; //角色类型:1-超级管理员；2-管理员；3-普通会员
	private Integer activeIndex; //活跃指数
	private Date joinTime; //加入时间
	private String status; //状态
	private SportUser clubMemberUser;//成员
	
	public void setClubMemberId(Long clubMemberId){
		this.clubMemberId=clubMemberId;
	}
	public Long getClubMemberId(){
		return clubMemberId;
	}
	public void setClubId(Long clubId){
		this.clubId=clubId;
	}
	public Long getClubId(){
		return clubId;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setRelationFlag(Integer relationFlag){
		this.relationFlag=relationFlag;
	}
	public Integer getRelationFlag(){
		return relationFlag;
	}
	public void setRoleType(Integer roleType){
		this.roleType=roleType;
	}
	public Integer getRoleType(){
		return roleType;
	}
	public void setActiveIndex(Integer activeIndex){
		this.activeIndex=activeIndex;
	}
	public Integer getActiveIndex(){
		return activeIndex;
	}
	public void setJoinTime(Date joinTime){
		this.joinTime=joinTime;
	}
	public Date getJoinTime(){
		return joinTime;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}
    public SportUser getClubMemberUser() {
        return clubMemberUser;
    }
    public void setClubMemberUser(SportUser clubMemberUser) {
        this.clubMemberUser = clubMemberUser;
    }
}

