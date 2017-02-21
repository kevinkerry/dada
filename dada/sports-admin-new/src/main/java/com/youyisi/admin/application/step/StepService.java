package com.youyisi.admin.application.step;

import com.youyisi.admin.domain.step.Step;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-06-02
 */
public interface StepService {

	Step save(Step entity);

	Step get(Long id);

	Integer delete(Step entity);

	Integer update(Step entity);

	Page<Step> queryPage(Page<Step> page);

	/**
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param clientType
	 *            1 Android 2 IOS
	 * @return Integer
	 */
	Integer countStep(Long beginTime, Long endTime, Integer clientType);

	/**
	 * 
	 * @param userId
	 * @param date
	 * @return Integer
	 */
	Integer countStepByUserId(Long userId, Long date);

	/**
	 * 
	 * @param userId
	 * @param beginStep
	 * @param endStep
	 * @return Integer
	 */
	Integer countStepNum(Long userId, Integer beginStep, Integer endStep);

	Integer freeze(Long stepId);

	Step sumStep(Long userId, Long beginTime, Long endTime);

}
