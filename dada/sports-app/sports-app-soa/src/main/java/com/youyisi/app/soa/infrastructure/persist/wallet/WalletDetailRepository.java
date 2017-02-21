package com.youyisi.app.soa.infrastructure.persist.wallet;

import java.util.Map;

import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.wallet.WalletDetail;
import com.youyisi.sports.domain.wallet.WalletDetailWithUser;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface WalletDetailRepository extends MybatisRepository<Long, WalletDetail> {

	Page<WalletDetailWithUser> queryPageRanklist(Page<WalletDetailWithUser> page);

	WalletDetail getByUserIdAndDate(Map<String, Object> map);
	
	WalletDetail getByUserIdAndDateAndType(Map<String, Object> map);

	Long getMyRanking(Map<String, Object> map);

	Double getSumByType(Map<String, Object> map);

	Double getSumThighByDate(Map<String, Object> map);
}

