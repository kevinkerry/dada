package com.youyisi.soa.infrastructure.persist.user;

import com.youyisi.sports.domain.user.User;
import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-05-03
 */
public interface UserRepository extends MybatisRepository<Long, User> {
}

