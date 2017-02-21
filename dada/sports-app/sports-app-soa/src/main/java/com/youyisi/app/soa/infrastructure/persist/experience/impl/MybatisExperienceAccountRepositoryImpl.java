package com.youyisi.app.soa.infrastructure.persist.experience.impl;

import org.springframework.stereotype.Repository;

import com.youyisi.app.soa.infrastructure.persist.experience.ExperienceAccountRepository;
import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.sports.domain.experience.ExperienceAccount;

/**
 * @author shuye
 * @time 2016-05-12
 */
@Repository
public class MybatisExperienceAccountRepositoryImpl extends MybatisOperations<Long, ExperienceAccount> implements ExperienceAccountRepository {

	@Override
	public ExperienceAccount getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserId"), userId);
	}
}

