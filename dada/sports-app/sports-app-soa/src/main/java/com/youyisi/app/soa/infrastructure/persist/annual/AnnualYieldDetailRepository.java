package com.youyisi.app.soa.infrastructure.persist.annual;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.annual.AnnualYieldDetail;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface AnnualYieldDetailRepository extends MybatisRepository<Long, AnnualYieldDetail> {

	AnnualYieldDetail getCurrentDay(Map<String, Object> map);

	Double getSumCurrentDayDistance(Map<String, Object> map);
}
