package com.youyisi.admin.application.distance;

import com.youyisi.admin.domain.distance.Distance;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-02
 */
public interface DistanceService {

	Distance save(Distance entity);

	Distance get(Long id);

	Integer delete(Distance entity);

	Integer update(Distance entity);

	Page<Distance> queryPage(Page<Distance> page);

	/**
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param clientType
	 *            类型 1：Android，2：ios
	 * @param result
	 *            结果集 1 跑步人数 2 总跑量(km)
	 * @return Double
	 */
	Double countDistance(Long beginTime, Long endTime, Integer clientType, Integer result);

	Double countDistanceByUserId(Long userId, Long date);

	Integer countDistanceNum(Long userId, Integer beginDistance, Integer endDistance);

}
