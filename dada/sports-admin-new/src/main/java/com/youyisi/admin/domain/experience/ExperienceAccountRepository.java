package com.youyisi.admin.domain.experience;

import com.youyisi.mybatis.MybatisRepository;

/**
 * @author shuye
 * @time 2016-05-14
 */
public interface ExperienceAccountRepository extends MybatisRepository<Long, ExperienceAccount> {

	ExperienceAccount getByUserId(Long userId);
}

