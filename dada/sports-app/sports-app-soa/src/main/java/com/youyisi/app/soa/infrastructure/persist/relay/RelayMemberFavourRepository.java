package com.youyisi.app.soa.infrastructure.persist.relay;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.relay.RelayMemberFavour;

/**
 * @author shuye
 * @time 2016-10-08
 */
public interface RelayMemberFavourRepository extends MybatisRepository<Long, RelayMemberFavour> {

	RelayMemberFavour getByMap(Map<String, Object> map);
}

