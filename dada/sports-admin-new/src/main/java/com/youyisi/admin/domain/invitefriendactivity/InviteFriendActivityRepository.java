package com.youyisi.admin.domain.invitefriendactivity;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-08-11
 */
public interface InviteFriendActivityRepository extends MybatisRepository<Long, InviteFriendActivity> {

	Integer delByActivityId(Long activityId);

	InviteFriendActivity getByActivityId(Long activityId);
}
