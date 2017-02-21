package com.youyisi.admin.infrastructure.persist.snatch;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.snatch.SnatchWin;
import com.youyisi.admin.domain.snatch.SnatchWinRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-09-22
 */
@Repository
public class MybatisSnatchWinRepositoryImpl extends MybatisOperations<Long, SnatchWin> implements SnatchWinRepository {

	@Override
	public Double getSnatchWinByActivityIdAndUserId(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".getSnatchWinByActivityIdAndUserId"), map);
	}
}
