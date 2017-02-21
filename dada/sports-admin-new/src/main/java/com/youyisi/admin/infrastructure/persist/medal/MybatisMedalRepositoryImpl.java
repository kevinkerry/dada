package com.youyisi.admin.infrastructure.persist.medal;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.medal.Medal;
import com.youyisi.admin.domain.medal.MedalRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-09-07
 */
@Repository
public class MybatisMedalRepositoryImpl extends MybatisOperations<Long, Medal> implements MedalRepository {

	@Override
	public List<Medal> getMedalList(Map<String, Object> map) {

		return getSqlSession().selectList(getNamespace().concat(".getMedalList"), map);
	}

	@Override
	public Integer delMedalById(Long id) {
		return getSqlSession().delete(getNamespace().concat(".delMedalById"), id);
	}
}
