package com.youyisi.app.soa.infrastructure.persist.area.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.area.AreaRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.area.Area;

/**
 * @author shuye
 * @time 2016-05-30
 */
@Repository
public class MybatisAreaRepositoryImpl extends MybatisOperations<Long, Area> implements AreaRepository {

	@Override
	public List<Area> getByParentId(Long parentId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getNamespace().concat(".getByParentId"), parentId);
	}
}

