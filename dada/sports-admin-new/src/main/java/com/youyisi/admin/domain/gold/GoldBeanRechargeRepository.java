package com.youyisi.admin.domain.gold;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-10-26
 */
public interface GoldBeanRechargeRepository extends MybatisRepository<Long, GoldBeanRecharge> {

	List<GoldBeanRecharge> getGoldBeanRechargeList();

	Integer deleteAll();

}
