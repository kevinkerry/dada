package com.youyisi.sports.domain.goldbean;

import com.youyisi.sports.domain.BaseEntity;

/**
 * @author shuye
 * @time 2016-10-21
 */


public class GoldBeanDetail extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8723172885394626528L;
	private Long userId; //
	private Integer type; //
	private Integer category; //
	private Double goldBean; //
	private Long date; //
	private Double result; //
	private Long createTime; //
	private String note; //
	private Long productId; //
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setType(Integer type){
		this.type=type;
	}
	public Integer getType(){
		return type;
	}
	public void setCategory(Integer category){
		this.category=category;
	}
	public Integer getCategory(){
		return category;
	}
	
	public void setDate(Long date){
		this.date=date;
	}
	public Long getDate(){
		return date;
	}
	public void setResult(Double result){
		this.result=result;
	}
	public Double getResult(){
		return result;
	}
	public void setCreateTime(Long createTime){
		this.createTime=createTime;
	}
	public Long getCreateTime(){
		return createTime;
	}
	public void setNote(String note){
		this.note=note;
	}
	public String getNote(){
		return note;
	}
	public void setProductId(Long productId){
		this.productId=productId;
	}
	public Long getProductId(){
		return productId;
	}
	public Double getGoldBean() {
		return goldBean;
	}
	public void setGoldBean(Double goldBean) {
		this.goldBean = goldBean;
	}
}

