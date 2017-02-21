package com.youyisi.app.soa.infrastructure.persist.relay.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.relay.RelayMemberFavourRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.relay.RelayMemberFavour;

/**
 * @author shuye
 * @time 2016-10-08
 */
@Repository
public class MybatisRelayMemberFavourRepositoryImpl extends MybatisOperations<Long, RelayMemberFavour> implements RelayMemberFavourRepository {

	@Override
	public RelayMemberFavour getByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByMap"), map);
	}
}

