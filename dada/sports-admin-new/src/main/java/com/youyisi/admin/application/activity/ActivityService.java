package com.youyisi.admin.application.activity;

import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-18
 */
public interface ActivityService {

	Activity save(Activity entity);

	Activity get(Long id);

	Integer delete(Activity entity);

	Integer update(Activity entity);

	Page<Activity> queryPage(Page<Activity> page);

	Integer addHugThigh(Activity activity);

	boolean validateDate(Integer type, Long date);

	Integer updateHugThigh(Activity activity);

	Integer delActivity(Long activityId);

	Integer addInviteFriend(Activity activity);

	Integer updateInviteFriend(Activity activity);

	Integer addRelayRace(Activity activity);

	Integer updateRelayRace(Activity activity);

	String getCorrelationActivityName(Long medalId);

	Integer addSnatchActivity(Activity activity);

	Integer updateSnatchActivity(Activity activity);
}
