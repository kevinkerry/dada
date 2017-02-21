package com.youyisi.app.soa.infrastructure.persist.goldbean.impl;

import org.springframework.stereotype.Repository;
import com.youyisi.sports.domain.goldbean.UserGoldBean;
import com.youyisi.app.soa.infrastructure.persist.goldbean.UserGoldBeanRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisUserGoldBeanRepositoryImpl extends MybatisOperations<Long, UserGoldBean> implements UserGoldBeanRepository {
}

