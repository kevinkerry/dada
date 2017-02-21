package com.youyisi.app.soa.infrastructure.persist.goldbean;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.goldbean.GoldBeanRecharge;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface GoldBeanRechargeRepository extends MybatisRepository<Long, GoldBeanRecharge> {

	List<GoldBeanRecharge> list();
}

