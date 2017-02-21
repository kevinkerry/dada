package com.youyisi.app.soa.infrastructure.persist.goldbean;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.goldbean.GoldBeanCash;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface GoldBeanCashRepository extends MybatisRepository<Long, GoldBeanCash> {

	Double getSumCash(Long userId);
}

