package com.youyisi.admin.infrastructure.persist.withdraw;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.withdraw.Withdraw;
import com.youyisi.admin.domain.withdraw.WithdrawRepository;
import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-05-21
 */
@Repository
public class MybatisWithdrawRepositoryImpl extends MybatisOperations<Long, Withdraw> implements WithdrawRepository {

	@Override
	public Page<Withdraw> queryPageWithdraw(Page<Withdraw> page) {
		List<Withdraw> selectList = getSqlSession().selectList(getNamespace().concat(".queryPageWithdraw"), page);
		page.setResult(selectList);
		return page;
	}

	@Override
	public Withdraw getByDrawbackNum(String withdrawNumber) {

		return getSqlSession().selectOne(getNamespace().concat(".getByDrawbackNum"), withdrawNumber);
	}

	@Override
	public Double sumWithdraw(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".sumWithdraw"), map);
	}

	@Override
	public List<Withdraw> getWithdrawList(Map<String, Object> map) {
		return getSqlSession().selectList(getNamespace().concat(".getWithdrawList"), map);
	}
}
