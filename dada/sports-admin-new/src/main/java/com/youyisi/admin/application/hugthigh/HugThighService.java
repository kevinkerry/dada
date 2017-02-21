package com.youyisi.admin.application.hugthigh;

import java.util.List;

import com.youyisi.admin.domain.hugthigh.HugThigh;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-07-19
 */
public interface HugThighService {

	HugThigh save(HugThigh entity);

	HugThigh get(Long id);

	Integer delete(HugThigh entity);

	Integer update(HugThigh entity);

	Page<HugThigh> queryPage(Page<HugThigh> page);

	/**
	 * 
	 * @param activityId
	 *            活动ID
	 * @param thighId
	 *            大腿ID
	 * @param resultType
	 *            查询类型 1 次数 2 人数
	 * @return Integer
	 */
	Integer countHugThigh(Long activityId, Long thighId, Integer resultType);

	/**
	 * 
	 * @param userId
	 * @return Integer
	 */
	Integer countHugThighByUserId(Long userId);

	List<HugThigh> query(HugThigh hugThigh);

}
