package com.youyisi.admin.infrastructure.persist.orders;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.orders.OrderFlow;
import com.youyisi.admin.domain.orders.OrderFlowRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-06-20
 */
@Repository
public class MybatisOrderFlowRepositoryImpl extends MybatisOperations<Long, OrderFlow> implements OrderFlowRepository {
}

