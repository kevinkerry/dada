package com.youyisi.app.soa.infrastructure.persist.orders.impl;

import org.springframework.stereotype.Repository;
import com.youyisi.sports.domain.orders.OrderFlow;
import com.youyisi.app.soa.infrastructure.persist.orders.OrderFlowRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Repository
public class MybatisOrderFlowRepositoryImpl extends MybatisOperations<Long, OrderFlow> implements OrderFlowRepository {
}

