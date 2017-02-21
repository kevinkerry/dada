package com.youyisi.app.soa.remote.wallet;

import com.youyisi.app.soa.exception.SoaException;
import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.lang.Page;
import com.youyisi.sports.domain.wallet.WalletDetail;
import com.youyisi.sports.domain.wallet.WalletDetailWithUser;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface WalletDetailServiceRemote extends BaseServiceInterface<Long, WalletDetail> {

	Page<WalletDetailWithUser> queryPageRanklist(Page<WalletDetailWithUser> page) throws SoaException;

	WalletDetail getByUserIdAndDateAndType(Long userId, Long date) throws SoaException;

	Long getMyRanking(Double money, Long date, Long id) throws SoaException;

	Double getSumByType(Integer type, Long userId) throws SoaException;

	Double getSumThighByDate(Long date, Long userId) throws SoaException;

}

