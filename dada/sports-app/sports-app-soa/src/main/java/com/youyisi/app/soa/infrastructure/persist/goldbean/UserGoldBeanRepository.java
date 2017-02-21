package com.youyisi.app.soa.infrastructure.persist.goldbean;

import com.youyisi.sports.domain.goldbean.UserGoldBean;
import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-10-21
 */
public interface UserGoldBeanRepository extends MybatisRepository<Long, UserGoldBean> {
}

