package com.youyisi.admin.application.gold;

import java.util.List;

import com.youyisi.admin.domain.gold.GoldBeanRecharge;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-26
 */
public interface GoldBeanRechargeService {

	GoldBeanRecharge save(GoldBeanRecharge entity);

	GoldBeanRecharge get(Long id);

	Integer delete(GoldBeanRecharge entity);

	Integer update(GoldBeanRecharge entity);

	Page<GoldBeanRecharge> queryPage(Page<GoldBeanRecharge> page);

	List<GoldBeanRecharge> getGoldBeanRechargeList();

	Integer saveGoldBeanRecharge(GoldBeanRecharge goldBeanRecharge);

	Integer deleteAll();

}
