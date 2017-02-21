package com.youyisi.admin.application.gift;

import java.util.List;

import com.youyisi.admin.domain.gift.Gift;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-06
 */
public interface GiftService {

	Gift save(Gift gift);

	Gift get(Long id);

	Integer delete(Gift gift);

	Integer update(Gift gift);

	Page<Gift> queryPage(Page<Gift> page);
	
	/**
	 * 批量保存
	 * 
	 * @param giftList {List}
	 */
	void saveBatch(List<Gift> giftList);

}

