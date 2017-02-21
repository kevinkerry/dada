package com.youyisi.app.soa.infrastructure.persist.experience;

import com.youyisi.mybatis.MybatisRepository;
import com.youyisi.sports.domain.experience.ExperienceAccount;

/**
 * @author shuye
 * @time 2016-05-12
 */
public interface ExperienceAccountRepository extends MybatisRepository<Long, ExperienceAccount> {

	ExperienceAccount getByUserId(Long userId);
}

