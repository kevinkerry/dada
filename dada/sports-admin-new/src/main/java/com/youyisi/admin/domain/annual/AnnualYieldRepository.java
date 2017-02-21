package com.youyisi.admin.domain.annual;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-05-14
 */
public interface AnnualYieldRepository extends MybatisRepository<Long, AnnualYield> {

	AnnualYield getByUserAndDate(Map<String, Object> map);
}

