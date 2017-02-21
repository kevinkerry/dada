package com.youyisi.admin.infrastructure.persist.step;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.step.Step;
import com.youyisi.admin.domain.step.StepRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-06-02
 */
@Repository
public class MybatisStepRepositoryImpl extends MybatisOperations<Long, Step> implements StepRepository {

	@Override
	public Integer countStep(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".countStep"), map);
	}

	@Override
	public Integer countStepByUserId(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".countStepByUserId"), map);
	}

	@Override
	public Integer countStepNum(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".countStepNum"), map);
	}

	@Override
	public Integer getTeamStep(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".getTeamStep"), map);
	}

	@Override
	public Integer getUserStep(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".getUserStep"), map);
	}

	@Override
	public Step sumStep(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".sumStep"), map);
	}
}
