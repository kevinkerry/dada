package com.youyisi.admin.domain.wallet;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-05-14
 */
public interface WalletRepository extends MybatisRepository<Long, Wallet> {

	Wallet getByUserId(Long userId);
}

