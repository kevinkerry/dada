package com.youyisi.app.soa.infrastructure.persist.wallet;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.wallet.Wallet;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface WalletRepository extends MybatisRepository<Long, Wallet> {

	Wallet getByUserId(Long userId);

	int updateByVersion(Wallet wallet, Double totalAsset);
}

