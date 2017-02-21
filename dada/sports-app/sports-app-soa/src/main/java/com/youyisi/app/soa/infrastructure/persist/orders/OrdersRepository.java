package com.youyisi.app.soa.infrastructure.persist.orders;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.orders.Orders;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface OrdersRepository extends MybatisRepository<Long, Orders> {

	Orders getByOrderNumber(String orderNumber);
}

