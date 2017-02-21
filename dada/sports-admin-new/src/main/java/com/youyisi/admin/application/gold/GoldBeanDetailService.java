package com.youyisi.admin.application.gold;

import com.youyisi.admin.domain.gold.GoldBeanDetail;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface GoldBeanDetailService {

	GoldBeanDetail save(GoldBeanDetail entity);

	GoldBeanDetail get(Long id);

	Integer delete(GoldBeanDetail entity);

	Integer update(GoldBeanDetail entity);

	Page<GoldBeanDetail> queryPage(Page<GoldBeanDetail> page);

}

