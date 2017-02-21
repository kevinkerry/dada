package com.youyisi.soa.infrastructure.persist.user.impl;

import org.springframework.stereotype.Repository;
import com.youyisi.sports.domain.user.User;
import com.youyisi.soa.infrastructure.persist.user.UserRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-05-03
 */
@Repository
public class MybatisUserRepositoryImpl extends MybatisOperations<Long, User> implements UserRepository {
}

