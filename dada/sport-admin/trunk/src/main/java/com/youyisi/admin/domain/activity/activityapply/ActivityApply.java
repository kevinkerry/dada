package com.youyisi.admin.domain.activity.activityapply;

import java.io.Serializable;
import java.util.Date;

import com.youyisi.admin.domain.member.Member;
import com.youyisi.lang.annotation.Id;
/**
 * @author shuye
 * @time 2015-08-19
 */


public class ActivityApply  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6336377590467577614L;
	@Id
	private Long applyId; //
	private Long activityId; //
	private Long userId; //
	private String phone; //
	private String name; //
	private String weixin; //
	private String workunit; //
	private String applyDesc; //
	private Date applyTime; //
	private Integer auditStatus; //
	private String auditComments; //
	private Date auditTime; //
	private String auditor; //
	private Member member;
	
	public void setApplyId(Long applyId){
		this.applyId=applyId;
	}
	public Long getApplyId(){
		return applyId;
	}
	public void setActivityId(Long activityId){
		this.activityId=activityId;
	}
	public Long getActivityId(){
		return activityId;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setWeixin(String weixin){
		this.weixin=weixin;
	}
	public String getWeixin(){
		return weixin;
	}
	public void setWorkunit(String workunit){
		this.workunit=workunit;
	}
	public String getWorkunit(){
		return workunit;
	}
	public void setApplyDesc(String applyDesc){
		this.applyDesc=applyDesc;
	}
	public String getApplyDesc(){
		return applyDesc;
	}
	public void setApplyTime(Date applyTime){
		this.applyTime=applyTime;
	}
	public Date getApplyTime(){
		return applyTime;
	}
	public void setAuditStatus(Integer auditStatus){
		this.auditStatus=auditStatus;
	}
	public Integer getAuditStatus(){
		return auditStatus;
	}
	public void setAuditComments(String auditComments){
		this.auditComments=auditComments;
	}
	public String getAuditComments(){
		return auditComments;
	}
	public void setAuditTime(Date auditTime){
		this.auditTime=auditTime;
	}
	public Date getAuditTime(){
		return auditTime;
	}
	public void setAuditor(String auditor){
		this.auditor=auditor;
	}
	public String getAuditor(){
		return auditor;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
}

