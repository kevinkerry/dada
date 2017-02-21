package com.youyisi.app.soa.infrastructure.persist.annual;

import java.util.Map;

import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.annual.AnnualYield;
import com.youyisi.sports.domain.annual.AnnualYieldWithRun;
import com.youyisi.sports.domain.annual.AnnualYieldWithWalletDetail;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface AnnualYieldRepository extends MybatisRepository<Long, AnnualYield> {

	AnnualYield getByUserIdAndDate(Long userId);
	
	Page<AnnualYieldWithWalletDetail> queryPageHistory(
			Page<AnnualYieldWithWalletDetail> page);

	AnnualYieldWithRun getAnnualYieldWithRunById(Long id);

	Page<AnnualYieldWithRun> queryPageDetailWithRun(
			Page<AnnualYieldWithRun> page);

	AnnualYield getByUserIdDate(Map<String, Object> map);
}

