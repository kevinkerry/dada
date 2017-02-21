package com.youyisi.admin.infrastructure.persist.annual;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.annual.AnnualYield;
import com.youyisi.admin.domain.annual.AnnualYieldRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-05-14
 */
@Repository
public class MybatisAnnualYieldRepositoryImpl extends MybatisOperations<Long, AnnualYield> implements AnnualYieldRepository {

	@Override
	public AnnualYield getByUserAndDate(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".getByUserAndDate"), map);
	}
}

