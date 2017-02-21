package com.youyisi.admin.domain.relay;

import java.util.List;
import java.util.Map;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-09-06
 */
public interface RelayTeamRepository extends MybatisRepository<Long, RelayTeam> {

	Double getRelayTeamSumDistance(Map<String, Object> map);

	List<RelayTeam> getRelayTeamByActivityId(Long activityId);

	Integer updateSettleByActivityId(Map<String, Object> map);

	Integer countRelayTeamNumByActivityId(Long activityId);

}
