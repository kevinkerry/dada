package com.youyisi.app.soa.infrastructure.persist.goldbean.impl;

import org.springframework.stereotype.Repository;
import com.youyisi.sports.domain.goldbean.GoldBeanDetail;
import com.youyisi.app.soa.infrastructure.persist.goldbean.GoldBeanDetailRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-21
 */
@Repository
public class MybatisGoldBeanDetailRepositoryImpl extends MybatisOperations<Long, GoldBeanDetail> implements GoldBeanDetailRepository {
}

