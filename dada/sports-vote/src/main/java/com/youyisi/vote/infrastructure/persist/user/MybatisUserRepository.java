package com.youyisi.vote.infrastructure.persist.user;

import org.springframework.stereotype.Repository;

import com.youyisi.mybatis.MybatisOperations;
import com.youyisi.vote.domain.user.User;
import com.youyisi.vote.domain.user.UserRepository;
/**
 * 
 * @author shuye
 *
 */
@Repository
public class MybatisUserRepository extends MybatisOperations<Long,User> implements UserRepository {

	@Override
	public void updateVoteNum(User user) {
		getSqlSession().update(getNamespace().concat(".updateVoteNum"), user);
	}

}
