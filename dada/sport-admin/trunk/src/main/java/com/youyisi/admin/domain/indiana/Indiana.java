package com.youyisi.admin.domain.indiana;

import java.io.Serializable;
import java.util.Date;

import com.youyisi.lang.annotation.Id;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
public class Indiana implements Serializable{
    
	private static final long serialVersionUID = 3474353808025321617L;
	
	@Id
    private Long indianaId; //夺宝ID
	private String title; //夺宝名称
	private Long creater; //创建人
	private Date createdTime; //创建时间
	private Integer limit; //夺宝人数限制
	private Integer status; //-1:未完成，1完成
	private Integer complete; //完成人数
	public void setIndianaId(Long indianaId){
		this.indianaId=indianaId;
	}
	public Long getIndianaId(){
		return indianaId;
	}
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setCreater(Long creater){
		this.creater=creater;
	}
	public Long getCreater(){
		return creater;
	}
	public void setCreatedTime(Date createdTime){
		this.createdTime=createdTime;
	}
	public Date getCreatedTime(){
		return createdTime;
	}
	public void setLimit(Integer limit){
		this.limit=limit;
	}
	public Integer getLimit(){
		return limit;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setComplete(Integer complete){
		this.complete=complete;
	}
	public Integer getComplete(){
		return complete;
	}
}

