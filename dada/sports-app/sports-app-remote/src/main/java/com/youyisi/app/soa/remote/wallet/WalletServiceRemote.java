package com.youyisi.app.soa.remote.wallet;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.orders.Orders;
import com.youyisi.sports.domain.wallet.Wallet;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface WalletServiceRemote extends BaseServiceInterface<Long, Wallet> {

	Wallet getByUserId(Long userId) throws SoaException;

	void recharge(Orders order) throws SoaException;
}
