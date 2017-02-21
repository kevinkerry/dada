package com.youyisi.app.soa.infrastructure.persist.goldbean;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.goldbean.GoldBean;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface GoldBeanRepository extends MybatisRepository<Long, GoldBean> {

	GoldBean getByUserId(Long userId);
}

