package com.youyisi.app.soa.infrastructure.persist.medal.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.medal.UserMedalRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.medal.UserMedal;
import com.youyisi.sports.domain.medal.UserMedalWithMedal;

/**
 * @author shuye
 * @time 2016-09-05
 */
@Repository
public class MybatisUserMedalRepositoryImpl extends MybatisOperations<Long, UserMedal> implements UserMedalRepository {

	@Override
	public List<UserMedalWithMedal> getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getNamespace().concat(".getByUserId"), userId);
	}

	@Override
	public UserMedal getByUserIdAndTypeCategory(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserIdAndTypeCategory"), map);
	}
}

