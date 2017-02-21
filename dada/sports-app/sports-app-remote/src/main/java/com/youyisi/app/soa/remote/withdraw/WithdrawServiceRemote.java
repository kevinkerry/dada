package com.youyisi.app.soa.remote.withdraw;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.wallet.Wallet;
import com.youyisi.sports.domain.withdraw.Withdraw;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface WithdrawServiceRemote extends BaseServiceInterface<Long, Withdraw> {

	Withdraw add(Withdraw withdraw, Wallet wallet) throws SoaException;

	Double currentWithdraw(Long userId)throws SoaException;

}

