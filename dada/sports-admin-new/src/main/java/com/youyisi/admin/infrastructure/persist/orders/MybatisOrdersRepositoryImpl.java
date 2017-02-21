package com.youyisi.admin.infrastructure.persist.orders;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.orders.Orders;
import com.youyisi.admin.domain.orders.OrdersRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-06-20
 */
@Repository
public class MybatisOrdersRepositoryImpl extends MybatisOperations<Long, Orders> implements OrdersRepository {

	@Override
	public Double countPay(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".countPay"), map);
	}
}
