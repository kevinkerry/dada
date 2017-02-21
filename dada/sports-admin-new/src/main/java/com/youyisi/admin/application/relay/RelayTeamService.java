package com.youyisi.admin.application.relay;

import java.util.List;

import com.youyisi.admin.domain.relay.RelayTeam;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-06
 */
public interface RelayTeamService {

	RelayTeam save(RelayTeam entity);

	RelayTeam get(Long id);

	Integer delete(RelayTeam entity);

	Integer update(RelayTeam entity);

	Page<RelayTeam> queryPage(Page<RelayTeam> page);

	Integer clearing(Long id);

	Double getRelayTeamSumDistance(Long teamId, Long activityId, Long userId);

	List<RelayTeam> getRelayTeamByActivityId(Long activityId);

	Integer updateSettleByActivityId(Long activityId, Integer settle);

	Integer countRelayTeamNumByActivityId(Long activityId);

	/** 获取奖金池 **/
	Double getBonusPool(Long activityId);

}
