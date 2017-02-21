package com.youyisi.admin.domain.gift;

import java.io.Serializable;

import com.youyisi.admin.domain.gift.gifttype.GiftType;
import com.youyisi.lang.annotation.Id;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
public class Gift implements Serializable{
    
	private static final long serialVersionUID = -1885910786267557213L;
	
	@Id
    private Long giftId; //奖品ID
	private String name; //奖品名称
	private Integer probability; //中奖概率
	private Long typeId; //奖品分类
	private Integer level; //奖品等级
	private GiftType type;//所属分类
	
	public GiftType getType() {
        return type;
    }
    public void setType(GiftType type) {
        this.type = type;
    }
    public void setGiftId(Long giftId){
		this.giftId=giftId;
	}
	public Long getGiftId(){
		return giftId;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setProbability(Integer probability){
		this.probability=probability;
	}
	public Integer getProbability(){
		return probability;
	}
	public void setTypeId(Long typeId){
		this.typeId=typeId;
	}
	public Long getTypeId(){
		return typeId;
	}
	public void setLevel(Integer level){
		this.level=level;
	}
	public Integer getLevel(){
		return level;
	}
}

