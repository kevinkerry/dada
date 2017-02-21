package com.youyisi.admin.infrastructure.persist.hugthigh;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.hugthigh.HugThigh;
import com.youyisi.admin.domain.hugthigh.HugThighRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-07-19
 */
@Repository
public class MybatisHugThighRepositoryImpl extends MybatisOperations<Long, HugThigh> implements HugThighRepository {

	@Override
	public Integer countHugThigh(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".countHugThigh"), map);
	}

	@Override
	public Integer countHugThighByUserId(Long userId) {
		return getSqlSession().selectOne(getNamespace().concat(".countHugThighByUserId"), userId);
	}
}
