package com.youyisi.admin.application.user;

import com.youyisi.admin.domain.user.UserSnatch;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-09-22
 */
public interface UserSnatchService {

	UserSnatch save(UserSnatch entity);

	UserSnatch get(Long id);

	Integer delete(UserSnatch entity);

	Integer update(UserSnatch entity);

	Page<UserSnatch> queryPage(Page<UserSnatch> page);

	/**
	 * 获取次数
	 * 
	 * @param activityId
	 * @param type
	 *            类型 1 人数 2次数
	 * @return Integer
	 */
	Integer getUserSnatchCount(Long activityId, Integer type);

}
