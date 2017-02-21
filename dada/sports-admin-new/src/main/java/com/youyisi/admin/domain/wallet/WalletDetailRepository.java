package com.youyisi.admin.domain.wallet;

import java.util.Map;

import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-05-14
 */
public interface WalletDetailRepository extends MybatisRepository<Long, WalletDetail> {

	Double countWallet(Map<String, Object> map);

	Page<WalletDetail> queryPageByUserId(Page<WalletDetail> page);

	Integer haveSettlement(Map<String, Object> map);

	Double getHugThighCommissionSum(Long date);

	Double sumWalletByDateAndType(Map<String, Object> map);

	Double sumWallet(Map<String, Object> map);

	Double sumWalletByUserId(Map<String, Object> map);

	Double getWithdrawBalance(Map<String, Object> map);
}
