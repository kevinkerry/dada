package com.youyisi.admin.domain.relay;

import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-10-10
 */
public interface RelayMemberFavourRepository extends MybatisRepository<Long, RelayMemberFavour> {

	Integer countNum(Map<String, Object> map);
}
