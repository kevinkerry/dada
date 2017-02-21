package com.youyisi.app.soa.infrastructure.persist.medal.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.medal.MedalRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.medal.Medal;

/**
 * @author shuye
 * @time 2016-09-05
 */
@Repository
public class MybatisMedalRepositoryImpl extends MybatisOperations<Long, Medal> implements MedalRepository {

	@Override
	public List<Medal> getNoHaveByUserId(Long userId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getNamespace().concat(".getNoHaveByUserId"), userId);
	}
}

