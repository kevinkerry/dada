package com.youyisi.app.soa.infrastructure.persist.withdraw.impl;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.withdraw.WithdrawRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.withdraw.Withdraw;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Repository
public class MybatisWithdrawRepositoryImpl extends MybatisOperations<Long, Withdraw> implements WithdrawRepository {

	@Override
	public Double currentWithdraw(Long userId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".currentWithdraw"), userId);
	}
}

