package com.youyisi.app.soa.infrastructure.persist.relay;

import java.util.List;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.relay.RelayLine;

/**
 * @author shuye
 * @time 2016-10-08
 */
public interface RelayLineRepository extends MybatisRepository<Long, RelayLine> {

	List<RelayLine> getByActivityId(Long activityId);
}

