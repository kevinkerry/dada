package com.youyisi.admin.infrastructure.persist.experience;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.experience.ExperienceAccount;
import com.youyisi.admin.domain.experience.ExperienceAccountRepository;
import com.youyisi.mybatis.MybatisOperations;

/**
 * @author shuye
 * @time 2016-05-14
 */
@Repository
public class MybatisExperienceAccountRepositoryImpl extends MybatisOperations<Long, ExperienceAccount>
		implements ExperienceAccountRepository {

	@Override
	public ExperienceAccount getByUserId(Long userId) {

		return getSqlSession().selectOne(getNamespace().concat(".getByUserId"), userId);
	}
}
