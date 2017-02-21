package com.youyisi.admin.application.gold;

import com.youyisi.admin.domain.gold.GoldBean;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface GoldBeanService {

	GoldBean save(GoldBean entity);

	GoldBean get(Long id);

	Integer delete(GoldBean entity);

	Integer update(GoldBean entity);

	Page<GoldBean> queryPage(Page<GoldBean> page);

}

