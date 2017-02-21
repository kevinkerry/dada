package com.youyisi.admin.domain.wallet;

import com.youyisi.lang.domain.BaseObject;

/**
 * @author shuye
 * @time 2016-05-14
 */


public class Wallet  extends BaseObject<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -468775657382006375L;
	private Long userId; //
	private Double totalAsset; //
	private Double principal; //
	private Double income; //
	public void setId(Long id){
		this.id=id;
	}
	public Long getId(){
		return id;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	public Long getUserId(){
		return userId;
	}
	public void setTotalAsset(Double totalAsset){
		this.totalAsset=totalAsset;
	}
	public Double getTotalAsset(){
		return totalAsset;
	}
	public void setPrincipal(Double principal){
		this.principal=principal;
	}
	public Double getPrincipal(){
		return principal;
	}
	public void setIncome(Double income){
		this.income=income;
	}
	public Double getIncome(){
		return income;
	}
}

