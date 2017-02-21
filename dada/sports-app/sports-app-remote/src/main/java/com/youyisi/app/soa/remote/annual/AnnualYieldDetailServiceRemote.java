package com.youyisi.app.soa.remote.annual;

import com.youyisi.app.soa.remote.BaseServiceInterface;
import com.youyisi.sports.domain.annual.AnnualYieldDetail;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface AnnualYieldDetailServiceRemote extends BaseServiceInterface<Long, AnnualYieldDetail> {

	AnnualYieldDetail getDetailByUserIdAndType(Long userId, Integer type);

	Double getSumCurrentDayDistance(Long userId);
}
