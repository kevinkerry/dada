package com.youyisi.app.soa.infrastructure.persist.annual.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.annual.AnnualYieldRepository;
import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.annual.AnnualYield;
import com.youyisi.sports.domain.annual.AnnualYieldWithRun;
import com.youyisi.sports.domain.annual.AnnualYieldWithWalletDetail;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Repository
public class MybatisAnnualYieldRepositoryImpl extends MybatisOperations<Long, AnnualYield>
		implements AnnualYieldRepository {

	@Override
	public AnnualYield getByUserIdAndDate(Long userId) {
		return getSqlSession().selectOne(getNamespace().concat(".getByUserIdAndDate"), userId);
	}

	@Override
	public Page<AnnualYieldWithWalletDetail> queryPageHistory(Page<AnnualYieldWithWalletDetail> page) {
		// TODO Auto-generated method stub
		List<AnnualYieldWithWalletDetail> result = getSqlSession()
				.selectList(getNamespace().concat(".queryPageHistory"), page);
		page.setResult(result);
		return page;
	}
	
	@Override
	public AnnualYieldWithRun getAnnualYieldWithRunById(Long id) {
		return getSqlSession().selectOne(getNamespace().concat(".getAnnualYieldWithRunById"), id);
	}

	@Override
	public Page<AnnualYieldWithRun> queryPageDetailWithRun(
			Page<AnnualYieldWithRun> page) {
		List<AnnualYieldWithRun> result = getSqlSession().selectList(getNamespace().concat(".queryPageDetailWithRun"), page);
		page.setResult(result);
		return page;
	}

	@Override
	public AnnualYield getByUserIdDate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserIdDate"), map);
	}
}
