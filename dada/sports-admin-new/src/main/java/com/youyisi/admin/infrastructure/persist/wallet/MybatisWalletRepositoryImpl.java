package com.youyisi.admin.infrastructure.persist.wallet;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.wallet.Wallet;
import com.youyisi.admin.domain.wallet.WalletRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-05-14
 */
@Repository
public class MybatisWalletRepositoryImpl extends MybatisOperations<Long, Wallet> implements WalletRepository {

	@Override
	public Wallet getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserId"), userId);
	}
}

