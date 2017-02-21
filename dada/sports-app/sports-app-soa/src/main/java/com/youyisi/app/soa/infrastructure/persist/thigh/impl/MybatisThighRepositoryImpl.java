package com.youyisi.app.soa.infrastructure.persist.thigh.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.thigh.ThighRepository;
import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.thigh.MyThigh;
import com.youyisi.sports.domain.thigh.Thigh;
import com.youyisi.sports.domain.thigh.ThighMoreInfo;
import com.youyisi.sports.domain.thigh.ThighRank;

/**
 * @author shuye
 * @time 2016-07-11
 */
@Repository
public class MybatisThighRepositoryImpl extends MybatisOperations<Long, Thigh> implements ThighRepository {

	@Override
	public Thigh getByActivityIdAndUserId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByActivityIdAndUserId"), map);
	}

	@Override
	public List<ThighMoreInfo> getList(Long activityId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getNamespace().concat(".getList"), activityId);
	}

	@Override
	public Long thighCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".thighCount"), map);
	}

	@Override
	public Page<MyThigh> queryPageMyThigh(Page<MyThigh> page) {
		List<MyThigh> list = getSqlSession().selectList(getNamespace().concat(".queryPageMyThigh"), page);
		page.setResult(list);
		return page;
	}

	@Override
	public List<ThighRank> getListThighRank(Page<ThighRank> page) {
		// TODO Auto-generated method stub
		List<ThighRank> list = getSqlSession().selectList(getNamespace().concat(".getListThighRank"), page);
		return list;
	}
}

