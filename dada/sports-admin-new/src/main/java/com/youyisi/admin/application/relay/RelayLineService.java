package com.youyisi.admin.application.relay;

import java.util.List;

import com.youyisi.admin.domain.relay.RelayLine;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-10-10
 */
public interface RelayLineService {

	RelayLine save(RelayLine entity);

	RelayLine get(Long id);

	Integer delete(RelayLine entity);

	Integer update(RelayLine entity);

	Page<RelayLine> queryPage(Page<RelayLine> page);

	Integer delByActivityId(Long activityId);

	List<RelayLine> getRelayLineList(Long activityId);

}
