package com.youyisi.app.soa.infrastructure.persist.relay;

import java.util.List;
import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.relay.RelayTeam;
import com.youyisi.sports.domain.relay.RelayTeamWithMore;

/**
 * @author shuye
 * @time 2016-09-05
 */
public interface RelayTeamRepository extends MybatisRepository<Long, RelayTeam> {

	RelayTeam getByActivityIdAndUserId(Map<String, Object> map);

	List<RelayTeamWithMore> getList(Long activityId);

	Integer getCountByActivityId(Long activityId);
}

