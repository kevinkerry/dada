package com.youyisi.app.soa.infrastructure.persist.activity.impl;

import org.springframework.stereotype.Repository;
import com.youyisi.sports.domain.activity.InviteFriendActivity;
import com.youyisi.app.soa.infrastructure.persist.activity.InviteFriendActivityRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-08-09
 */
@Repository
public class MybatisInviteFriendActivityRepositoryImpl extends MybatisOperations<Long, InviteFriendActivity> implements InviteFriendActivityRepository {
}

