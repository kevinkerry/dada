package com.youyisi.app.soa.infrastructure.persist.wallet.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.wallet.WalletDetailRepository;
import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.wallet.WalletDetail;
import com.youyisi.sports.domain.wallet.WalletDetailWithUser;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Repository
public class MybatisWalletDetailRepositoryImpl extends MybatisOperations<Long, WalletDetail> implements WalletDetailRepository {

	@Override
	public Page<WalletDetailWithUser> queryPageRanklist(
			Page<WalletDetailWithUser> page) {
		List<WalletDetailWithUser> result = getSqlSession().selectList(getNamespace().concat(".queryPageRanklist"), page);
		page.setResult(result);
		return page;
	}

	@Override
	public WalletDetail getByUserIdAndDate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserIdAndDate"), map);
	}

	@Override
	public Long getMyRanking(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return  getSqlSession().selectOne(getNamespace().concat(".getMyRanking"), map);
	}

	@Override
	public WalletDetail getByUserIdAndDateAndType(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserIdAndDateAndType"), map);
	}
	@Override
	public Double getSumByType(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getSumByType"), map);
	}

	@Override
	public Double getSumThighByDate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getSumThighByDate"), map);
	}
	
}

