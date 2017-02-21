package com.youyisi.app.soa.remote.annual;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.annual.AnnualYield;
import com.youyisi.sports.domain.annual.AnnualYieldWithRun;
import com.youyisi.sports.domain.annual.AnnualYieldWithWalletDetail;
import com.youyisi.sports.domain.wallet.WalletDetail;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface AnnualYieldServiceRemote extends BaseServiceInterface<Long, AnnualYield> {

	AnnualYield getByUserIdAndDate(Long userId) throws SoaException;

	Page<AnnualYieldWithWalletDetail> queryPageHistory(Page<AnnualYieldWithWalletDetail> page) throws SoaException;

	WalletDetail predictFortune(Long UserId, AnnualYieldWithWalletDetail annualYieldWithWalletDetail)
			throws SoaException;

	AnnualYieldWithRun getDetailWithRun(Long id)throws SoaException;

	Page<AnnualYieldWithRun> queryPageDetailWithRun(Page<AnnualYieldWithRun> page) throws SoaException;

	AnnualYield getByUserIdDate(Long userId, Long date);

}
