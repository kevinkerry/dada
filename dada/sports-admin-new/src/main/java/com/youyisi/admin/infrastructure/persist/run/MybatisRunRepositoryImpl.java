package com.youyisi.admin.infrastructure.persist.run;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.run.Run;
import com.youyisi.admin.domain.run.RunRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-06-27
 */
@Repository
public class MybatisRunRepositoryImpl extends MybatisOperations<Long, Run> implements RunRepository {

	@Override
	public Integer countRunByTotalTime(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".countRunByTotalTime"), map);
	}

	@Override
	public Integer countRunByType(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".countRunByType"), map);
	}

	@Override
	public Run getMaxRun(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".getMaxRun"), map);
	}

	@Override
	public Run sumDistance(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".sumDistance"), map);
	}
}
