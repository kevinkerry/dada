package com.youyisi.admin.application.wallet;

import com.youyisi.admin.domain.wallet.Wallet;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-14
 */
public interface WalletService {

	Wallet save(Wallet entity);

	Wallet get(Long id);

	Integer delete(Wallet entity);

	Integer update(Wallet entity);

	Page<Wallet> queryPage(Page<Wallet> page);

	Wallet getByUserId(Long userId);

}

