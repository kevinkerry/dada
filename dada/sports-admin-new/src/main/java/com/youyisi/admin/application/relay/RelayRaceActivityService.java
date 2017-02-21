package com.youyisi.admin.application.relay;

import com.youyisi.admin.domain.relay.RelayRaceActivity;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-06
 */
public interface RelayRaceActivityService {

	RelayRaceActivity save(RelayRaceActivity entity);

	RelayRaceActivity get(Long id);

	Integer delete(RelayRaceActivity entity);

	Integer update(RelayRaceActivity entity);

	Page<RelayRaceActivity> queryPage(Page<RelayRaceActivity> page);

	RelayRaceActivity getByActivityId(Long activityId);

	Integer delByActivityId(Long activityId);

}
