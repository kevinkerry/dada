package com.youyisi.admin.infrastructure.persist.gold;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.gold.GoldBeanRecharge;
import com.youyisi.admin.domain.gold.GoldBeanRechargeRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-26
 */
@Repository
public class MybatisGoldBeanRechargeRepositoryImpl extends MybatisOperations<Long, GoldBeanRecharge>
		implements GoldBeanRechargeRepository {

	@Override
	public List<GoldBeanRecharge> getGoldBeanRechargeList() {
		return getSqlSession().selectList(getNamespace().concat(".getGoldBeanRechargeList"));
	}

	@Override
	public Integer deleteAll() {
		return getSqlSession().delete(getNamespace().concat(".deleteAll"));
	}
}
