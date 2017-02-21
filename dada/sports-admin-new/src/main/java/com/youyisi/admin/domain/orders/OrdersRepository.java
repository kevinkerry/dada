package com.youyisi.admin.domain.orders;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-06-20
 */
public interface OrdersRepository extends MybatisRepository<Long, Orders> {

	Double countPay(Map<String, Object> map);
}
