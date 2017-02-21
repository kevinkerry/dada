package com.youyisi.admin.domain.indiana.indianadetail;

import java.io.Serializable;
import java.util.Date;

import com.youyisi.admin.domain.gift.Gift;
import com.youyisi.admin.domain.indiana.Indiana;
import com.youyisi.admin.domain.sportuser.SportUser;
import com.youyisi.lang.annotation.Id;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
public class IndianaDetail implements Serializable{
    
	private static final long serialVersionUID = 7889792485746214468L;
	
	@Id
    private Long indianaDetailId; //夺宝获奖详情ID
	private Long userId; //用户ID
	private Long giftId; //奖品ID
	private Long indianaId; //夺宝ID
	private Date createTime; //获奖时间
	private Byte status; //0:未发奖 ，1：已发放
	private SportUser user;//用户
	private Gift gift; //奖品
	private Indiana indiana;
	
	public SportUser getUser() {
        return user;
    }
    public void setUser(SportUser user) {
        this.user = user;
    }
    public Gift getGift() {
        return gift;
    }
    public void setGift(Gift gift) {
        this.gift = gift;
    }
    public Indiana getIndiana() {
        return indiana;
    }
    public void setIndiana(Indiana indiana) {
        this.indiana = indiana;
    }
    public void setIndianaDetailId(Long indianaDetailId){
		this.indianaDetailId=indianaDetailId;
	}
	public Long getIndianaDetailId(){
		return indianaDetailId;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setGiftId(Long giftId){
		this.giftId=giftId;
	}
	public Long getGiftId(){
		return giftId;
	}
	public void setIndianaId(Long indianaId){
		this.indianaId=indianaId;
	}
	public Long getIndianaId(){
		return indianaId;
	}
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setStatus(Byte status){
		this.status=status;
	}
	public Byte getStatus(){
		return status;
	}
}

