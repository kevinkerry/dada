package com.youyisi.app.soa.infrastructure.persist.thigh.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.thigh.HugThighRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.thigh.HugThigh;
import com.youyisi.sports.domain.thigh.HugThighWithUser;

/**
 * @author shuye
 * @time 2016-07-11
 */
@Repository
public class MybatisHugThighRepositoryImpl extends MybatisOperations<Long, HugThigh> implements HugThighRepository {

	@Override
	public Integer getHugThighCount(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".getHugThighCount"), map);
	}

	@Override
	public HugThigh getByUserAndThighId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserAndThighId"), map);
	}

	@Override
	public List<HugThighWithUser> getListByThighId(Long thighId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getNamespace().concat(".getListByThighId"), thighId);
	}

	@Override
	public Integer getByUserIdAndThighId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserIdAndThighId"), map);
	}

	@Override
	public Integer getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getCount"), map);
	}
}

