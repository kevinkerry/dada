package com.youyisi.admin.application.annual;

import com.youyisi.admin.domain.annual.AnnualYieldDetail;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-14
 */
public interface AnnualYieldDetailService {

	AnnualYieldDetail save(AnnualYieldDetail entity);

	AnnualYieldDetail get(Long id);

	Integer delete(AnnualYieldDetail entity);

	Integer update(AnnualYieldDetail entity);

	Page<AnnualYieldDetail> queryPage(Page<AnnualYieldDetail> page);

}

