package com.youyisi.app.soa.infrastructure.persist.orders.impl;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.orders.OrdersRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.orders.Orders;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Repository
public class MybatisOrdersRepositoryImpl extends MybatisOperations<Long, Orders> implements OrdersRepository {

	@Override
	public Orders getByOrderNumber(String orderNumber) {
		return getSqlSession().selectOne(getNamespace().concat(".getByOrderNumber"), orderNumber);
	}
}

