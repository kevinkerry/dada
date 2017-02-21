package com.youyisi.admin.application.run;

import com.youyisi.admin.domain.run.Run;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-27
 */
public interface RunService {

	Run save(Run entity);

	Run get(Long id);

	Integer delete(Run entity);

	Integer update(Run entity);

	Page<Run> queryPage(Page<Run> page);

	Page<Run> queryPageByUserId(Page<Run> page);

	Integer countRunByTotalTime(Long userId, Long date);

	Integer countRunByType(Long userId, Integer type);

	Run getMaxRun(Long userId, Long dateForDay, Double avspeed, Double maxspeed);

	Run getThighRun(Long userId, Long activityId);

	Run sumDistance(Long userId, Integer type, Long beginTime, Long endTime);
}
