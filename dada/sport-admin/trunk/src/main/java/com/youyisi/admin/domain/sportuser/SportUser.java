package com.youyisi.admin.domain.sportuser;

import java.io.Serializable;
import java.util.Date;

import com.youyisi.admin.domain.member.Member;
import com.youyisi.lang.annotation.Id;
 
/**
 * 
 * @author yinjunfeng
 * @date Jul 9, 2015
 */
public class SportUser  implements Serializable{
    
	private static final long serialVersionUID = 3066582288805687109L;
	
	@Id
    private Long userId;
	private String userCode;
	private String userName;
	private String userPwd;
	private Integer userType;
	private Integer regSource;
	private String externalIdentifier;
	private String clientType;
	private Date registerTime;
	private String status;
	private String clientId;
	private Member member;
	
	public void setUserId(Long userId){
	    this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setUserCode(String userCode){
	    this.userCode=userCode;
	}
	public String getUserCode(){
		return userCode;
	}
	public void setUserName(String userName){
	    this.userName=userName;
	}
	public String getUserName(){
		return userName;
	}
	public void setUserPwd(String userPwd){
	    this.userPwd=userPwd;
	}
	public String getUserPwd(){
		return userPwd;
	}
	public void setUserType(Integer userType){
	    this.userType=userType;
	}
	public Integer getUserType(){
		return userType;
	}
	public void setRegSource(Integer regSource){
	    this.regSource=regSource;
	}
	public Integer getRegSource(){
		return regSource;
	}
	public void setExternalIdentifier(String externalIdentifier){
	    this.externalIdentifier=externalIdentifier;
	}
	public String getExternalIdentifier(){
		return externalIdentifier;
	}
	public void setClientType(String clientType){
	    this.clientType=clientType;
	}
	public String getClientType(){
		return clientType;
	}
	public void setRegisterTime(Date registerTime){
	    this.registerTime=registerTime;
	}
	public Date getRegisterTime(){
		return registerTime;
	}
	public void setStatus(String status){
	    this.status=status;
	}
	public String getStatus(){
		return status;
	}
	public void setClientId(String clientId){
	    this.clientId=clientId;
	}
	public String getClientId(){
		return clientId;
	}
    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }
}

