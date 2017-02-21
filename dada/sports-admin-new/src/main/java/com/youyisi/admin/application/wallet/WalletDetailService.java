package com.youyisi.admin.application.wallet;

import java.util.List;
import java.util.Map;

import com.youyisi.admin.domain.wallet.WalletDetail;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-14
 */
public interface WalletDetailService {

	WalletDetail save(WalletDetail entity);

	WalletDetail get(Long id);

	Integer delete(WalletDetail entity);

	Integer update(WalletDetail entity);

	Page<WalletDetail> queryPage(Page<WalletDetail> page);

	List<WalletDetail> query(WalletDetail entity);

	/**
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param walletType
	 *            类型 1 充值 2提现 6：抱大腿支出
	 * @param clientType
	 *            类型 1：Android，2：ios
	 * @param result
	 *            结果集 1 次数 2 总额（元）
	 * @return Double
	 */
	Double countWallet(Long beginTime, Long endTime, Integer walletType, Integer clientType, Integer result);

	Page<WalletDetail> queryPageByUserId(Page<WalletDetail> page);

	Integer haveSettlement(Map<String, Object> map);

	Double getHugThighCommissionSum(Long activityId);

	Double sumWalletByDateAndType(Long date, Integer type);

	Double sumWallet(Long userId, Long date, Integer type);

	Double sumWalletByUserId(Long userId, Integer type);

	/**
	 * 获取提现余额
	 * 
	 * @param userId
	 * @param money
	 * @param date
	 * @return Double
	 */
	Double getWithdrawBalance(Long userId, Double money, Long date, Long createTime);
}
