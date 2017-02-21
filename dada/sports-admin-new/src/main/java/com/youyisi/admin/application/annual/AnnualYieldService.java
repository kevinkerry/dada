package com.youyisi.admin.application.annual;

import com.youyisi.admin.domain.annual.AnnualYield;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-14
 */
public interface AnnualYieldService {

	AnnualYield save(AnnualYield entity);

	AnnualYield get(Long id);

	Integer delete(AnnualYield entity);

	Integer update(AnnualYield entity);

	Page<AnnualYield> queryPage(Page<AnnualYield> page);

	AnnualYield getByUserAndDate(Long userId, Long date);

}

