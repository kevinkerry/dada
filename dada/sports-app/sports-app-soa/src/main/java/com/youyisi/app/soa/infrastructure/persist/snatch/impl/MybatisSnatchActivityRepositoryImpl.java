package com.youyisi.app.soa.infrastructure.persist.snatch.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.snatch.SnatchActivityRepository;
import com.youyisi.lang.Page;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.snatch.SnatchActivity;
import com.youyisi.sports.domain.snatch.SnatchActivityWithSnatchWinWithMore;

/**
 * @author shuye
 * @time 2016-09-21
 */
@Repository
public class MybatisSnatchActivityRepositoryImpl extends MybatisOperations<Long, SnatchActivity> implements SnatchActivityRepository {

	@Override
	public Page<SnatchActivityWithSnatchWinWithMore> queryPageForHistory(
			Page<SnatchActivityWithSnatchWinWithMore> page) {
		// TODO Auto-generated method stub
		List<SnatchActivityWithSnatchWinWithMore> list = getSqlSession().selectList(getNamespace().concat(".queryPageForHistory"), page);
		page.setResult(list);
		return page;
	}
}

