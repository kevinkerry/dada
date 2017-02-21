package com.youyisi.app.soa.infrastructure.persist.snatch.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.snatch.SnatchWinRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.snatch.SnatchWin;
import com.youyisi.sports.domain.snatch.SnatchWinWithMore;

/**
 * @author shuye
 * @time 2016-09-21
 */
@Repository
public class MybatisSnatchWinRepositoryImpl extends MybatisOperations<Long, SnatchWin> implements SnatchWinRepository {

	@Override
	public List<SnatchWinWithMore> winResult(Long activityId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getNamespace().concat(".winResult"), activityId);
	}

	@Override
	public SnatchWin getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserId"), userId);
	}
}

