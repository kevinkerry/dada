package com.youyisi.admin.domain.relay;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-10-10
 */
public interface RelayLineRepository extends MybatisRepository<Long, RelayLine> {

	Integer delByActivityId(Long activityId);

	List<RelayLine> getRelayLineList(Long activityId);
}
