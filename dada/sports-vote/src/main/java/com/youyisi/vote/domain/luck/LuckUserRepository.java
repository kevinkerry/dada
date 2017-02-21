package com.youyisi.vote.domain.luck;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2015-09-15
 */
public interface LuckUserRepository extends MybatisRepository<Long, LuckUser> {

	LuckUser getByUserId(Long userId);
}

