package com.youyisi.admin.infrastructure.persist.thigh;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.thigh.Thigh;
import com.youyisi.admin.domain.thigh.ThighRepository;
import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-07-18
 */
@Repository
public class MybatisThighRepositoryImpl extends MybatisOperations<Long, Thigh> implements ThighRepository {

	@Override
	public Integer countThigh(Map<String, Object> map) {

		return getSqlSession().selectOne(getNamespace().concat(".countThigh"), map);
	}

	@Override
	public Integer countThighByActivityId(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".countThighByActivityId"), map);
	}

	@Override
	public Page<Thigh> queryPageByUserId(Page<Thigh> page) {
		List<Thigh> selectList = getSqlSession().selectList(getNamespace().concat(".queryPageByUserId"), page);
		page.setResult(selectList);
		return page;
	}

	@Override
	public Integer countThighByUserId(Long userId) {

		return getSqlSession().selectOne(getNamespace().concat(".countThighByUserId"), userId);
	}
}
