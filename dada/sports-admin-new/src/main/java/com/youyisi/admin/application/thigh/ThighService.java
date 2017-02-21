package com.youyisi.admin.application.thigh;

import com.youyisi.admin.domain.thigh.Thigh;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-18
 */
public interface ThighService {

	Thigh save(Thigh entity);

	Thigh get(Long id);

	Integer delete(Thigh entity);

	Integer update(Thigh entity);

	Page<Thigh> queryPage(Page<Thigh> page);

	/**
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param clientType
	 *            1安卓 2IOS
	 * @return Integer
	 */
	Integer countThigh(Long beginTime, Long endTime, Integer clientType);

	/**
	 * 
	 * @param activityId
	 *            活动ID
	 * @param status
	 *            状态
	 * @return Integer
	 */
	Integer countThighByActivityId(Long activityId, Integer status, Integer type);

	Integer countThighByUserId(Long userId);

	Page<Thigh> queryPageByUserId(Page<Thigh> page);

	/**
	 * 统计用户参加活动次数
	 * 
	 * @param userId
	 * @return Integer
	 */
	Integer countJoin(Long userId);

}
