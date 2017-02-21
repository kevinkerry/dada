package com.youyisi.admin.infrastructure.persist.user;

import org.springframework.stereotype.Repository;
import com.youyisi.admin.domain.user.UserMedal;
import com.youyisi.admin.domain.user.UserMedalRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-09-08
 */
@Repository
public class MybatisUserMedalRepositoryImpl extends MybatisOperations<Long, UserMedal> implements UserMedalRepository {
}

