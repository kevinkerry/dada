package com.youyisi.admin.infrastructure.persist.relay;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.relay.RelayMemberFavour;
import com.youyisi.admin.domain.relay.RelayMemberFavourRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-10-10
 */
@Repository
public class MybatisRelayMemberFavourRepositoryImpl extends MybatisOperations<Long, RelayMemberFavour>
		implements RelayMemberFavourRepository {

	@Override
	public Integer countNum(Map<String, Object> map) {
		return getSqlSession().selectOne(getNamespace().concat(".countNum"), map);
	}
}
