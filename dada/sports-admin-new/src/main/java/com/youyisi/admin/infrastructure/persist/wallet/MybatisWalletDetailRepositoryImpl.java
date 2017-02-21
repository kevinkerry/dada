package com.youyisi.admin.infrastructure.persist.wallet;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.wallet.WalletDetail;
import com.youyisi.admin.domain.wallet.WalletDetailRepository;
import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-05-14
 */
@Repository
public class MybatisWalletDetailRepositoryImpl extends MybatisOperations<Long, WalletDetail>
		implements WalletDetailRepository {

	@Override
	public Double countWallet(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".countWallet"), map);
	}

	@Override
	public Page<WalletDetail> queryPageByUserId(Page<WalletDetail> page) {
		List<WalletDetail> selectList = getSqlSession().selectList(getNamespace().concat(".queryPageByUserId"), page);
		page.setResult(selectList);
		return page;
	}

	@Override
	public Integer haveSettlement(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".haveSettlement"), map);
	}

	@Override
	public Double getHugThighCommissionSum(Long date) {
		return getSqlSession().selectOne(getNamespace().concat(".getHugThighCommissionSum"), date);
	}

	@Override
	public Double sumWalletByDateAndType(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".sumWalletByDateAndType"), map);
	}

	@Override
	public Double sumWallet(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".sumWallet"), map);
	}

	@Override
	public Double sumWalletByUserId(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".sumWalletByUserId"), map);
	}

	@Override
	public Double getWithdrawBalance(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".getWithdrawBalance"), map);
	}
}
