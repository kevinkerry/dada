package com.youyisi.admin.application.gift.gifttype;

import java.util.List;
import java.util.Map;

import com.youyisi.admin.domain.gift.gifttype.GiftType;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
public interface GiftTypeService {

	GiftType save(GiftType giftType);

	GiftType get(Long id);

	Integer delete(GiftType giftType);

	Integer update(GiftType giftType);

	Page<GiftType> queryPage(Page<GiftType> page);
	
	/**
     * 查询所有的记录
     * 
     * @param params查询参数
     * @return List<GiftType>
     */
    List<GiftType> getAll(Map<String, Object> params);

}

