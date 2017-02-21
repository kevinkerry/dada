package com.youyisi.vote.infrastructure.persist.luck;

import org.springframework.stereotype.Repository;

import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.vote.domain.luck.LuckUser;
import com.youyisi.vote.domain.luck.LuckUserRepository;

/**
 * @author shuye
 * @time 2015-09-15
 */
@Repository
public class MybatisLuckUserRepositoryImpl extends MybatisOperations<Long, LuckUser> implements LuckUserRepository {

	@Override
	public LuckUser getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getNamespace().concat(".getByUserId"),userId);
	}
}

