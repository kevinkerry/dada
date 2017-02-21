package com.youyisi.app.soa.infrastructure.persist.wallet.impl;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.wallet.WalletRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.wallet.Wallet;
import com.youyisi.sports.domain.wallet.WalletWithVersion;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Repository
public class MybatisWalletRepositoryImpl extends MybatisOperations<Long, Wallet> implements WalletRepository {

	@Override
	public Wallet getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserId"), userId);
	}

	@Override
	public int updateByVersion(Wallet wallet, Double totalAsset) {
		WalletWithVersion wv = new WalletWithVersion();
		wv.setId(wallet.getId());
		wv.setIncome(wallet.getIncome());
		wv.setPrincipal(wallet.getPrincipal());
		wv.setTotalAsset(wallet.getTotalAsset());
		wv.setUserId(wallet.getUserId());
		wv.setVersion(totalAsset);
		return getSqlSession().update(getNamespace().concat(".updateByVersion"), wv);
	}
}

