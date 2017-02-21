package com.youyisi.admin.application.gold;

import com.youyisi.admin.domain.gold.GoldBeanCash;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface GoldBeanCashService {

	GoldBeanCash save(GoldBeanCash entity);

	GoldBeanCash get(Long id);

	Integer delete(GoldBeanCash entity);

	Integer update(GoldBeanCash entity);

	Page<GoldBeanCash> queryPage(Page<GoldBeanCash> page);

}

