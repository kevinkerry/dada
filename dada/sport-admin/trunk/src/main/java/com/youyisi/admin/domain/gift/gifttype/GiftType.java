package com.youyisi.admin.domain.gift.gifttype;

import java.io.Serializable;

import com.youyisi.lang.annotation.Id;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
public class GiftType implements Serializable{
    
	private static final long serialVersionUID = -1166704070311381948L;
	
	@Id
    private Long giftTypeId; //奖品类型ID
	private Integer limit; //奖品总数
	private Integer surplus; //奖品剩余量
	private Long parentTypeId; //奖品父分类
	private String typeName; //奖品
	private Integer value; //奖品面值
	private GiftType parentType; //奖品父类
	
	public GiftType getParentType() {
        return parentType;
    }
    public void setParentType(GiftType parentType) {
        this.parentType = parentType;
    }
    public void setGiftTypeId(Long giftTypeId){
		this.giftTypeId=giftTypeId;
	}
	public Long getGiftTypeId(){
		return giftTypeId;
	}
	public void setLimit(Integer limit){
		this.limit=limit;
	}
	public Integer getLimit(){
		return limit;
	}
	public void setSurplus(Integer surplus){
		this.surplus=surplus;
	}
	public Integer getSurplus(){
		return surplus;
	}
	public void setParentTypeId(Long parentTypeId){
		this.parentTypeId=parentTypeId;
	}
	public Long getParentTypeId(){
		return parentTypeId;
	}
	public void setTypeName(String typeName){
		this.typeName=typeName;
	}
	public String getTypeName(){
		return typeName;
	}
	public void setValue(Integer value){
		this.value=value;
	}
	public Integer getValue(){
		return value;
	}
}

