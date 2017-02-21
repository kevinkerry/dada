package com.youyisi.admin.infrastructure.persist.invitefriendactivity;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.invitefriendactivity.InviteFriendActivity;
import com.youyisi.admin.domain.invitefriendactivity.InviteFriendActivityRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-08-11
 */
@Repository
public class MybatisInviteFriendActivityRepositoryImpl extends MybatisOperations<Long, InviteFriendActivity>
		implements InviteFriendActivityRepository {

	@Override
	public Integer delByActivityId(Long activityId) {
		return getSqlSession().delete(getNamespace().concat(".delByActivityId"), activityId);
	}

	@Override
	public InviteFriendActivity getByActivityId(Long activityId) {
		return getSqlSession().selectOne(getNamespace().concat(".getByActivityId"), activityId);
	}
}
