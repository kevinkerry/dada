package com.youyisi.app.soa.infrastructure.persist.step.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.step.StepRepository;
import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.step.Step;
import com.youyisi.sports.domain.step.StepWithUser;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Repository
public class MybatisStepRepositoryImpl extends MybatisOperations<Long, Step> implements StepRepository {

	@Override
	public Step getByUserIdAndDate(Long userId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserIdAndDate"), userId);
	}

	@Override
	public Page<StepWithUser> queryPageRanklist(Page<StepWithUser> page) {
		List<StepWithUser> results = getSqlSession().selectList(getNamespace().concat(".queryPageRanklist"), page);
		page.setResult(results);
		return page;
	}
	
	@Override
	public Long getMyRanking(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getMyRanking"), map);
	}

	@Override
	public Step getByUserIdAndAnyDate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserIdAndAnyDate"), map);
	}

	@Override
	public Integer getTeamStep(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getTeamStep"), map);
	}

	@Override
	public Integer getUserStep(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getUserStep"), map);
	}
	
}

